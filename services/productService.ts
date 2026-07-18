import axios from 'axios'
import { Product, Review, CreateProductRequest, ReviewRequest } from '@/types/product'

const API_BASE_URL = process.env.NEXT_PUBLIC_API_URL || 'http://localhost:8080'

const api = axios.create({
  baseURL: `${API_BASE_URL}/api`,
  headers: {
    'Content-Type': 'application/json',
  },
})

export const productService = {
  async getAllProducts(): Promise<Product[]> {
    const response = await api.get('/products')
    return response.data
  },

  async getProduct(id: number): Promise<Product> {
    const response = await api.get(`/products/${id}`)
    return response.data
  },

  async createProduct(product: CreateProductRequest): Promise<Product> {
    const response = await api.post('/products', product)
    return response.data
  },

  async addReview(productId: number, review: ReviewRequest): Promise<Product> {
    const response = await api.post(`/products/${productId}/reviews`, review)
    return response.data
  },

  async getProductReviews(productId: number): Promise<Review[]> {
    const response = await api.get(`/products/${productId}/reviews`)
    return response.data
  },

  async getProductReport(productId: number): Promise<string> {
    const response = await api.get(`/products/${productId}/report`)
    return response.data
  },

  async changeLocale(locale: string): Promise<void> {
    await api.put(`/products/locale/${locale}`)
  },

  async getSupportedLocales(): Promise<string[]> {
    const response = await api.get('/products/locales')
    return response.data
  },
}
