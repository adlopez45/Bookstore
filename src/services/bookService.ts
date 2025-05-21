// project/src/services/bookService.ts

export interface ApiBook {
  bookId: number;
  title: string;
  isbn: string;
  price: number;
  imageUrl: string;
  stock: number;
  categoryId: number; // Cambiado de categoryName a categoryId
  authorName: string;
  description: string;
  publicationDate: string;
}

const API_BASE_URL = '/api';

export const fetchAllBooksFromApi = async (): Promise<ApiBook[]> => {
  const response = await fetch(`${API_BASE_URL}/books`);
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({ message: `Error fetching books: ${response.statusText}` }));
    throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
  }
  return response.json();
};

export const fetchBookByIdFromApi = async (id: string): Promise<ApiBook> => {
  const response = await fetch(`${API_BASE_URL}/books/${id}`);
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({ message: `Error fetching book ${id}: ${response.statusText}` }));
    throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
  }
  return response.json();
};