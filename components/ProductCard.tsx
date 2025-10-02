'use client'

import { Product } from '@/types/product'
import { format } from 'date-fns'
import clsx from 'clsx'

interface ProductCardProps {
  product: Product
  onSelect: (product: Product) => void
  isSelected: boolean
}

export function ProductCard({ product, onSelect, isSelected }: ProductCardProps) {
  const formatPrice = (price: number) => {
    return new Intl.NumberFormat('en-US', {
      style: 'currency',
      currency: 'USD',
    }).format(price)
  }

  const formatDate = (dateString: string | null) => {
    if (!dateString) return null
    try {
      return format(new Date(dateString), 'MMM dd, yyyy')
    } catch {
      return dateString
    }
  }

  return (
    <div
      onClick={() => onSelect(product)}
      className={clsx(
        'card cursor-pointer transition-all duration-200 hover:shadow-lg',
        isSelected && 'ring-2 ring-primary-500 ring-offset-2'
      )}
    >
      <div className="flex justify-between items-start mb-4">
        <div>
          <h3 className="text-lg font-semibold text-gray-900 mb-1">
            {product.name}
          </h3>
          <div className="flex items-center space-x-2">
            <span className={clsx(
              'px-2 py-1 text-xs font-medium rounded-full',
              product.type === 'FOOD' 
                ? 'bg-green-100 text-green-800' 
                : 'bg-blue-100 text-blue-800'
            )}>
              {product.type}
            </span>
          </div>
        </div>
        <div className="text-right">
          <div className="text-lg font-bold text-gray-900">
            {formatPrice(product.price)}
          </div>
          {product.discount > 0 && (
            <div className="text-sm text-green-600">
              Save {formatPrice(product.discount)}
            </div>
          )}
        </div>
      </div>

      <div className="flex items-center justify-between">
        <div className="flex items-center space-x-2">
          <span className="text-yellow-400 text-lg" title={`Rating: ${product.rating}`}>
            {product.ratingStars}
          </span>
          <span className="text-sm text-gray-600">({product.rating})</span>
        </div>
        
        {product.bestBefore && (
          <div className="text-sm text-gray-500">
            Best before: {formatDate(product.bestBefore)}
          </div>
        )}
      </div>
    </div>
  )
}
