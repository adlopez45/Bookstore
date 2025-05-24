// src/services/bookService.ts
import api from './api';

export interface ApiBook {
  bookId: number;
  title: string;
  author: string;
  isbn: string;
  price: number;
  imageUrl: string;
  stock: number;
  categoryId: number;
  categoryName: string;
  description: string;
  publicationDate: string;
  status: number;
}

export const fetchAllBooksFromApi = async (): Promise<ApiBook[]> => {
  try {
    const response = await api.get<ApiBook[]>('/api/books');
    return response.data;
  } catch (error) {
    console.error('Error fetching books:', error);
    throw new Error('Failed to fetch books');
  }
};

export const fetchBookByIdFromApi = async (id: string): Promise<ApiBook> => {
  try {
    const response = await api.get<ApiBook>(`/api/books/${id}`);
    return response.data;
  } catch (error) {
    console.error('Error fetching book details:', error);
    throw new Error('Failed to fetch book details');
  }
};

export const searchBooksByTitle = async (title: string): Promise<ApiBook[]> => {
  try {
    const response = await api.get<ApiBook[]>(`/api/books/title?name=${encodeURIComponent(title)}`);
    return response.data;
  } catch (error) {
    console.error('Error searching books:', error);
    throw new Error('Failed to search books');
  }
};

export const searchBooksByAuthor = async (author: string): Promise<ApiBook[]> => {
  try {
    const response = await api.get<ApiBook[]>(`/api/books/author?author=${encodeURIComponent(author)}`);
    return response.data;
  } catch (error) {
    console.error('Error searching books by author:', error);
    throw new Error('Failed to search books by author');
  }
};