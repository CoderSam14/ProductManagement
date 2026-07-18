export interface Product {
  id: number
  name: string
  price: number
  rating: string
  ratingStars: string
  bestBefore: string | null
  discount: number
  type: 'FOOD' | 'DRINK'
}

export interface Review {
  rating: string
  comment: string
}

export interface CreateProductRequest {
  id: number
  name: string
  price: number
  rating: string
  bestBefore?: string
}

export interface ReviewRequest {
  rating: string
  comment: string
}
