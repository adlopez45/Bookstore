import React, { useEffect, useState } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { ShoppingCart } from 'lucide-react';
import { useCart } from '../contexts/CartContext';
import { fetchBookByIdFromApi, ApiBook } from '../services/bookService';
import { Book, BookCategory } from '../types/Book';
import LoadingSpinner from '../components/LoadingSpinner';

const mapApiBookToFrontendBook = (apiBook: ApiBook): Book => {
  return {
    id: String(apiBook.bookId),
    title: apiBook.title,
    author: apiBook.author,
    description: apiBook.description || 'No description available',
    coverImage: apiBook.imageUrl,
    price: `${apiBook.price.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}`,
    numericPrice: apiBook.price,
    isbn: apiBook.isbn,
    category: (apiBook.categoryName as BookCategory) || BookCategory.Fiction,
    publishDate: apiBook.publicationDate ? new Date(apiBook.publicationDate).toISOString().split('T')[0] : "Fecha no disponible",
  };
};

const BookDetailsPage: React.FC = () => {
  const { id } = useParams<{ id: string }>();
  const navigate = useNavigate();
  const { addToCart } = useCart();

  const [book, setBook] = useState<Book | null>(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const loadBookDetails = async () => {
      if (!id) {
        setError("No se proporcionó un ID de libro.");
        setIsLoading(false);
        return;
      }

      try {
        setIsLoading(true);
        setError(null);
        const apiBookData = await fetchBookByIdFromApi(id);
        setBook(mapApiBookToFrontendBook(apiBookData));
      } catch (err) {
        setError(err instanceof Error ? err.message : "Error desconocido al cargar detalles del libro.");
      } finally {
        setIsLoading(false);
      }
    };

    loadBookDetails();
  }, [id]);

  const handleAddToCart = () => {
    if (book) {
      addToCart(book);
    }
  };

  const buyNow = () => {
    if (book) {
      addToCart(book);
      navigate('/cart');
    }
  };

  if (isLoading) {
    return (
      <div className="flex justify-center items-center min-h-[60vh]">
        <LoadingSpinner size="large" />
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center py-12">
        <p className="text-red-500 mb-4">{error}</p>
        <Link to="/shop" className="text-[#7c6a9a] hover:underline">
          Volver a la tienda
        </Link>
      </div>
    );
  }

  if (!book) {
    return (
      <div className="text-center py-12">
        <p className="text-gray-600 mb-4">Libro no encontrado</p>
        <Link to="/shop" className="text-[#7c6a9a] hover:underline">
          Volver a la tienda
        </Link>
      </div>
    );
  }

  return (
    <div className="page-transition bg-white rounded-lg shadow-md p-8">
      <div className="flex justify-start mb-6">
        <Link to="/shop" className="text-[#7c6a9a] hover:underline flex items-center">
          &larr; Volver a la tienda
        </Link>
      </div>

      <div className="grid grid-cols-1 md:grid-cols-2 gap-12">
        <div className="flex items-center justify-center">
          <div className="w-full max-w-xs sm:max-w-sm">
            <img
              src={book.coverImage}
              alt={book.title}
              className="w-full h-auto object-contain shadow-lg rounded max-h-[600px]"
            />
          </div>
        </div>

        <div className="flex flex-col">
          <h1 className="text-3xl lg:text-4xl font-bold mb-2">{book.title}</h1>
          <h2 className="text-xl text-gray-600 mb-6">por {book.author}</h2>

          <div className="bg-gray-50 p-6 rounded-lg mb-8 shadow-sm">
            <div className="flex justify-between items-center mb-3 pb-3 border-b">
              <span className="text-gray-700 font-medium">PRECIO:</span>
              <span className="text-2xl font-semibold text-[#7c6a9a]">{book.price}</span>
            </div>
            <div className="space-y-2 text-sm">
              <div className="flex justify-between">
                <span className="text-gray-600">ISBN:</span>
                <span className="text-gray-800">{book.isbn}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Categoría:</span>
                <span className="text-gray-800">{book.category}</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Fecha de Publicación:</span>
                <span className="text-gray-800">{book.publishDate}</span>
              </div>
            </div>
          </div>

          <h3 className="text-lg font-semibold mb-2">Descripción</h3>
          <p className="text-gray-700 mb-8 leading-relaxed text-justify">{book.description}</p>

          <div className="mt-auto grid grid-cols-1 sm:grid-cols-2 gap-4">
            <button
              onClick={buyNow}
              className="btn-primary py-3"
            >
              Comprar Ahora
            </button>
            <button
              onClick={handleAddToCart}
              className="btn-secondary py-3 flex items-center justify-center gap-2"
            >
              <ShoppingCart size={18} /> Añadir al Carrito
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default BookDetailsPage;