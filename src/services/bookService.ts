// project/src/services/bookService.ts

// Este tipo DEBE COINCIDIR con los campos expuestos por tu BookResponseDTO.java
export interface ApiBook {
  bookId: number;
  title: string;
  isbn: string;
  price: number;
  imageUrl: string;
  stock: number;
  categoryName: string | null;
  authorName: string;
  description: string;
  publicationDate: string; // Las fechas usualmente llegan como strings ISO desde el backend
  // status?: number; // Si lo necesitas
}

const API_BASE_URL = '/api'; // Usamos la ruta relativa gracias al proxy de Vite

export const fetchAllBooksFromApi = async (): Promise<ApiBook[]> => {
  const response = await fetch(`${API_BASE_URL}/books`);
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({ message: `Error fetching books: ${response.statusText}` }));
    throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
  }
  return response.json();
};

// ESTA ES LA FUNCIÓN EN LA QUE NOS ENFOCAMOS AHORA
export const fetchBookByIdFromApi = async (id: string): Promise<ApiBook> => {
  const response = await fetch(`<span class="math-inline">\{API\_BASE\_URL\}/books/</span>{id}`);
  if (!response.ok) {
    const errorData = await response.json().catch(() => ({ message: `Error fetching book ${id}: ${response.statusText}` }));
    throw new Error(errorData.message || `HTTP error! status: ${response.status}`);
  }
  return response.json();
};