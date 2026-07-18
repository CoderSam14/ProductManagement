'use client'

import { useState, useEffect } from 'react'
import { ProductList } from '@/components/ProductList'
import { AddProductForm } from '@/components/AddProductForm'
import { Header } from '@/components/Header'
import { ProductDetails } from '@/components/ProductDetails'
import { Product } from '@/types/product'
import { productService } from '@/services/productService'

export default function Home() {
  const [products, setProducts] = useState<Product[]>([])
  const [selectedProduct, setSelectedProduct] = useState<Product | null>(null)
  const [showAddForm, setShowAddForm] = useState(false)
  const [loading, setLoading] = useState(true)
  const [locale, setLocale] = useState('en-GB')

  useEffect(() => {
    loadProducts()
  }, [])

  const loadProducts = async () => {
    try {
      setLoading(true)
      const data = await productService.getAllProducts()
      setProducts(data)
    } catch (error) {
      console.error('Failed to load products:', error)
    } finally {
      setLoading(false)
    }
  }

  const handleAddProduct = async (productData: any) => {
    try {
      await productService.createProduct(productData)
      setShowAddForm(false)
      loadProducts()
    } catch (error) {
      console.error('Failed to add product:', error)
    }
  }

  const handleProductSelect = (product: Product) => {
    setSelectedProduct(product)
  }

  const handleAddReview = async (productId: number, reviewData: any) => {
    try {
      await productService.addReview(productId, reviewData)
      loadProducts()
      // Refresh selected product if it's the one being reviewed
      if (selectedProduct?.id === productId) {
        const updatedProduct = await productService.getProduct(productId)
        setSelectedProduct(updatedProduct)
      }
    } catch (error) {
      console.error('Failed to add review:', error)
    }
  }

  const handleLocaleChange = async (newLocale: string) => {
    try {
      await productService.changeLocale(newLocale)
      setLocale(newLocale)
      loadProducts()
    } catch (error) {
      console.error('Failed to change locale:', error)
    }
  }

  return (
    <div className="min-h-screen bg-gray-50">
      <Header 
        onAddProduct={() => setShowAddForm(true)}
        onLocaleChange={handleLocaleChange}
        currentLocale={locale}
      />
      
      <main className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 py-8">
        <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
          {/* Products List */}
          <div className="lg:col-span-2">
            <ProductList
              products={products}
              loading={loading}
              onProductSelect={handleProductSelect}
              selectedProductId={selectedProduct?.id}
            />
          </div>
          
          {/* Product Details or Add Form */}
          <div className="lg:col-span-1">
            {showAddForm ? (
              <AddProductForm
                onSubmit={handleAddProduct}
                onCancel={() => setShowAddForm(false)}
              />
            ) : selectedProduct ? (
              <ProductDetails
                product={selectedProduct}
                onAddReview={handleAddReview}
              />
            ) : (
              <div className="card text-center text-gray-500">
                <h3 className="text-lg font-medium mb-2">No Product Selected</h3>
                <p>Select a product from the list to view details and reviews.</p>
              </div>
            )}
          </div>
        </div>
      </main>
    </div>
  )
}
