'use client'

import { useState, useEffect } from 'react'
import { Product, Review } from '@/types/product'
import { productService } from '@/services/productService'
import { AddReviewForm } from './AddReviewForm'
import { format } from 'date-fns'

interface ProductDetailsProps {
  product: Product
  onAddReview: (productId: number, reviewData: any) => void
}

export function ProductDetails({ product, onAddReview }: ProductDetailsProps) {
  const [reviews, setReviews] = useState<Review[]>([])
  const [showAddReview, setShowAddReview] = useState(false)
  const [loading, setLoading] = useState(false)

  useEffect(() => {
    loadReviews()
  }, [product.id])

  const loadReviews = async () => {
    try {
      setLoading(true)
      const reviewsData = await productService.getProductReviews(product.id)
      setReviews(reviewsData)
    } catch (error) {
      console.error('Failed to load reviews:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleAddReview = async (reviewData: any) => {
    await onAddReview(product.id, reviewData)
    setShowAddReview(false)
    loadReviews()
  }

  const formatPrice = (price: number) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
    }).format(price)
  }

  const formatDate = (dateString: string | null) => {
    if (!dateString) return 'No expiry'
    try {
      return format(new Date(dateString), 'MMM dd, yyyy')
    } catch {
      return dateString
    }
  }

  const getRatingText = (rating: string) => {
    const ratingMap: { [key: string]: string } = {
      'NOT_RATED': 'Not Rated',
      'ONE_STAR': '1 Star',
      'TWO_STAR': '2 Stars',
      'THREE_STAR': '3 Stars',
      'FOUR_STAR': '4 Stars',
      'FIVE_STAR': '5 Stars',
    }
    return ratingMap[rating] || rating
  }

  return (
    <div className="space-y-6">
      {/* Product Details Card */}
      <div className="card">
        <div className="flex justify-between items-start mb-4">
          <div>
            <h2 className="text-2xl font-bold text-gray-900 mb-2">
              {product.name}
            </h2>
            <span className={`px-3 py-1 text-sm font-medium rounded-full ${
              product.type === 'FOOD' 
                ? 'bg-green-100 text-green-800' 
                : 'bg-blue-100 text-blue-800'
            }`}>
              {product.type}
            </span>
          </div>
          <div className="text-right">
            <div className="text-2xl font-bold text-gray-900">
              {formatPrice(product.price)}
            </div>
            {product.discount > 0 && (
              <div className="text-lg text-green-600">
                Discount: {formatPrice(product.discount)}
              </div>
            )}
          </div>
        </div>

        <div className="space-y-3">
          <div className="flex items-center space-x-3">
            <span className="text-2xl" title={getRatingText(product.rating)}>
              {product.ratingStars}
            </span>
            <span className="text-lg text-gray-700">
              {getRatingText(product.rating)}
            </span>
          </div>
          
          <div className="text-sm text-gray-600">
            <strong>ID:</strong> {product.id}
          </div>
          
          <div className="text-sm text-gray-600">
            <strong>Best Before:</strong> {formatDate(product.bestBefore)}
          </div>
        </div>
      </div>

      {/* Reviews Section */}
      <div className="card">
        <div className="flex justify-between items-center mb-4">
          <h3 className="text-lg font-semibold text-gray-900">
            Reviews ({reviews.length})
          </h3>
          <button
            onClick={() => setShowAddReview(!showAddReview)}
            className="btn-primary text-sm"
          >
            {showAddReview ? 'Cancel' : 'Add Review'}
          </button>
        </div>

        {showAddReview && (
          <div className="mb-6">
            <AddReviewForm onSubmit={handleAddReview} />
          </div>
        )}

        {loading ? (
          <div className="space-y-4">
            {[...Array(3)].map((_, i) => (
              <div key={i} className="animate-pulse">
                <div className="h-4 bg-gray-200 rounded w-3/4 mb-2"></div>
                <div className="h-4 bg-gray-200 rounded w-1/2"></div>
              </div>
            ))}
          </div>
        ) : reviews.length === 0 ? (
          <div className="text-center py-8 text-gray-500">
            <div className="text-4xl mb-2">💭</div>
            <p>No reviews yet. Be the first to review this product!</p>
          </div>
        ) : (
          <div className="space-y-4">
            {reviews.map((review, index) => (
              <div key={index} className="border-l-4 border-primary-200 pl-4 py-2">
                <div className="flex items-center space-x-2 mb-2">
                  <span className="text-yellow-400">
                    {getRatingStars(review.rating)}
                  </span>
                  <span className="text-sm text-gray-600">
                    {getRatingText(review.rating)}
                  </span>
                </div>
                <p className="text-gray-700">{review.comment}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </div>
  )
}

function getRatingStars(rating: string): string {
  const ratingMap: { [key: string]: string } = {
    'NOT_RATED': '☆☆☆☆☆',
    'ONE_STAR': '★☆☆☆☆',
    'TWO_STAR': '★★☆☆☆',
    'THREE_STAR': '★★★☆☆',
    'FOUR_STAR': '★★★★☆',
    'FIVE_STAR': '★★★★★',
  }
  return ratingMap[rating] || '☆☆☆☆☆'
}
