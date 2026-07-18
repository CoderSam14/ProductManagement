'use client'

import { useState } from 'react'

interface AddReviewFormProps {
  onSubmit: (reviewData: any) => void
}

export function AddReviewForm({ onSubmit }: AddReviewFormProps) {
  const [formData, setFormData] = useState({
    rating: 'FIVE_STAR',
    comment: ''
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    onSubmit(formData)
    setFormData({ rating: 'FIVE_STAR', comment: '' })
  }

  const handleChange = (e: React.ChangeEvent<HTMLSelectElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target
    setFormData(prev => ({
      ...prev,
      [name]: value
    }))
  }

  return (
    <div className="border border-gray-200 rounded-lg p-4 bg-gray-50">
      <h4 className="text-md font-medium text-gray-900 mb-4">Add Your Review</h4>
      
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="rating" className="block text-sm font-medium text-gray-700 mb-1">
            Rating
          </label>
          <select
            id="rating"
            name="rating"
            value={formData.rating}
            onChange={handleChange}
            className="w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
          >
            <option value="ONE_STAR">★☆☆☆☆ (1 Star)</option>
            <option value="TWO_STAR">★★☆☆☆ (2 Stars)</option>
            <option value="THREE_STAR">★★★☆☆ (3 Stars)</option>
            <option value="FOUR_STAR">★★★★☆ (4 Stars)</option>
            <option value="FIVE_STAR">★★★★★ (5 Stars)</option>
          </select>
        </div>

        <div>
          <label htmlFor="comment" className="block text-sm font-medium text-gray-700 mb-1">
            Comment
          </label>
          <textarea
            id="comment"
            name="comment"
            required
            rows={3}
            value={formData.comment}
            onChange={handleChange}
            className="w-full rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
            placeholder="Share your thoughts about this product..."
          />
        </div>

        <button
          type="submit"
          className="w-full btn-primary"
        >
          Submit Review
        </button>
      </form>
    </div>
  )
}
