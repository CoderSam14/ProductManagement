'use client'

import { useState } from 'react'
import { XMarkIcon } from '@heroicons/react/24/outline'

interface AddProductFormProps {
  onSubmit: (productData: any) => void
  onCancel: () => void
}

export function AddProductForm({ onSubmit, onCancel }: AddProductFormProps) {
  const [formData, setFormData] = useState({
    id: '',
    name: '',
    price: '',
    rating: 'NOT_RATED',
    bestBefore: '',
    hasExpiry: false
  })

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault()
    
    const productData = {
      id: parseInt(formData.id),
      name: formData.name,
      price: parseFloat(formData.price),
      rating: formData.rating,
      ...(formData.hasExpiry && formData.bestBefore && {
        bestBefore: formData.bestBefore
      })
    }
    
    onSubmit(productData)
  }

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) => {
    const { name, value, type } = e.target
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? (e.target as HTMLInputElement).checked : value
    }))
  }

  return (
    <div className="card">
      <div className="flex justify-between items-center mb-6">
        <h2 className="text-xl font-semibold text-gray-900">Add New Product</h2>
        <button
          onClick={onCancel}
          className="text-gray-400 hover:text-gray-600"
        >
          <XMarkIcon className="h-6 w-6" />
        </button>
      </div>

      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label htmlFor="id" className="block text-sm font-medium text-gray-700">
            Product ID
          </label>
          <input
            type="number"
            id="id"
            name="id"
            required
            value={formData.id}
            onChange={handleChange}
            className="input-field"
            placeholder="Enter unique product ID"
          />
        </div>

        <div>
          <label htmlFor="name" className="block text-sm font-medium text-gray-700">
            Product Name
          </label>
          <input
            type="text"
            id="name"
            name="name"
            required
            value={formData.name}
            onChange={handleChange}
            className="input-field"
            placeholder="Enter product name"
          />
        </div>

        <div>
          <label htmlFor="price" className="block text-sm font-medium text-gray-700">
            Price
          </label>
          <input
            type="number"
            id="price"
            name="price"
            required
            step="0.01"
            min="0"
            value={formData.price}
            onChange={handleChange}
            className="input-field"
            placeholder="0.00"
          />
        </div>

        <div>
          <label htmlFor="rating" className="block text-sm font-medium text-gray-700">
            Initial Rating
          </label>
          <select
            id="rating"
            name="rating"
            value={formData.rating}
            onChange={handleChange}
            className="input-field"
          >
            <option value="NOT_RATED">Not Rated</option>
            <option value="ONE_STAR">1 Star</option>
            <option value="TWO_STAR">2 Stars</option>
            <option value="THREE_STAR">3 Stars</option>
            <option value="FOUR_STAR">4 Stars</option>
            <option value="FIVE_STAR">5 Stars</option>
          </select>
        </div>

        <div className="flex items-center space-x-2">
          <input
            type="checkbox"
            id="hasExpiry"
            name="hasExpiry"
            checked={formData.hasExpiry}
            onChange={handleChange}
            className="rounded border-gray-300 text-primary-600 focus:ring-primary-500"
          />
          <label htmlFor="hasExpiry" className="text-sm font-medium text-gray-700">
            This product has an expiry date (Food item)
          </label>
        </div>

        {formData.hasExpiry && (
          <div>
            <label htmlFor="bestBefore" className="block text-sm font-medium text-gray-700">
              Best Before Date
            </label>
            <input
              type="date"
              id="bestBefore"
              name="bestBefore"
              value={formData.bestBefore}
              onChange={handleChange}
              className="input-field"
            />
          </div>
        )}

        <div className="flex space-x-3 pt-4">
          <button
            type="submit"
            className="btn-primary flex-1"
          >
            Add Product
          </button>
          <button
            type="button"
            onClick={onCancel}
            className="btn-secondary flex-1"
          >
            Cancel
          </button>
        </div>
      </form>
    </div>
  )
}
