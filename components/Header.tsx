'use client'

import { useState, useEffect } from 'react'
import { PlusIcon, GlobeAltIcon } from '@heroicons/react/24/outline'
import { productService } from '@/services/productService'

interface HeaderProps {
  onAddProduct: () => void
  onLocaleChange: (locale: string) => void
  currentLocale: string
}

export function Header({ onAddProduct, onLocaleChange, currentLocale }: HeaderProps) {
  const [supportedLocales, setSupportedLocales] = useState<string[]>([])

  useEffect(() => {
    loadSupportedLocales()
  }, [])

  const loadSupportedLocales = async () => {
    try {
      const locales = await productService.getSupportedLocales()
      setSupportedLocales(Array.from(locales))
    } catch (error) {
      console.error('Failed to load supported locales:', error)
    }
  }

  return (
    <header className="bg-white shadow-sm border-b border-gray-200">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between items-center py-6">
          <div>
            <h1 className="text-3xl font-bold text-gray-900">
              Product Management System
            </h1>
            <p className="text-gray-600 mt-1">
              Manage your products, reviews, and ratings
            </p>
          </div>
          
          <div className="flex items-center space-x-4">
            {/* Locale Selector */}
            <div className="flex items-center space-x-2">
              <GlobeAltIcon className="h-5 w-5 text-gray-500" />
              <select
                value={currentLocale}
                onChange={(e) => onLocaleChange(e.target.value)}
                className="rounded-md border-gray-300 shadow-sm focus:border-primary-500 focus:ring-primary-500"
              >
                {supportedLocales.map((locale) => (
                  <option key={locale} value={locale}>
                    {locale}
                  </option>
                ))}
              </select>
            </div>
            
            {/* Add Product Button */}
            <button
              onClick={onAddProduct}
              className="btn-primary flex items-center space-x-2"
            >
              <PlusIcon className="h-5 w-5" />
              <span>Add Product</span>
            </button>
          </div>
        </div>
      </div>
    </header>
  )
}
