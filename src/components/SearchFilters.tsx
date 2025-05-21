import React from 'react';
import { BookCategory, PriceRange } from '../types/Book';

interface SearchFiltersProps {
  selectedCategory: BookCategory | 'all';
  setSelectedCategory: (category: BookCategory | 'all') => void;
  selectedPriceRange: PriceRange | null;
  setSelectedPriceRange: (range: PriceRange | null) => void;
  selectedAuthor: string;
  setSelectedAuthor: (author: string) => void;
  dateRange: { start: string; end: string };
  setDateRange: (range: { start: string; end: string }) => void;
  authors: string[];
}

const SearchFilters: React.FC<SearchFiltersProps> = ({
  selectedCategory,
  setSelectedCategory,
  selectedPriceRange,
  setSelectedPriceRange,
  selectedAuthor,
  setSelectedAuthor,
  dateRange,
  setDateRange,
  authors,
}) => {
  return (
    <div className="bg-white rounded-lg shadow-md p-6 mb-8">
      <h2 className="text-lg font-semibold mb-4">Filtros</h2>
      
      <div className="space-y-6">
        {/* Category Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Categorías</h3>
          <div className="flex flex-wrap gap-2">
            <button
              onClick={() => setSelectedCategory('all')}
              className={`px-3 py-1 rounded-full text-sm transition-colors ${
                selectedCategory === 'all'
                  ? 'bg-[#7c6a9a] text-white'
                  : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
              }`}
            >
              Todas
            </button>
            {Object.entries(BookCategory).map(([key, value]) => (
              <button
                key={value}
                onClick={() => setSelectedCategory(value as BookCategory)}
                className={`px-3 py-1 rounded-full text-sm transition-colors ${
                  selectedCategory === value
                    ? 'bg-[#7c6a9a] text-white'
                    : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                }`}
              >
                {key}
              </button>
            ))}
          </div>
        </div>

        {/* Price Range Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Rango de Precio</h3>
          <div className="space-y-2">
            <button
              onClick={() => setSelectedPriceRange(null)}
              className={`w-full text-left px-3 py-2 rounded text-sm transition-colors ${
                !selectedPriceRange
                  ? 'bg-[#7c6a9a] text-white'
                  : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
              }`}
            >
              Todos los precios
            </button>
            {Object.values(PriceRange).map((range) => (
              <button
                key={range}
                onClick={() => setSelectedPriceRange(range)}
                className={`w-full text-left px-3 py-2 rounded text-sm transition-colors ${
                  selectedPriceRange === range
                    ? 'bg-[#7c6a9a] text-white'
                    : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                }`}
              >
                {range}
              </button>
            ))}
          </div>
        </div>

        {/* Author Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Autor</h3>
          <select
            value={selectedAuthor}
            onChange={(e) => setSelectedAuthor(e.target.value)}
            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-[#9d8fbb]"
          >
            <option value="">Todos los autores</option>
            {authors.map((author) => (
              <option key={author} value={author}>
                {author}
              </option>
            ))}
          </select>
        </div>

        {/* Publication Date Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Fecha de Publicación</h3>
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs text-gray-500 mb-1">Desde</label>
              <input
                type="date"
                value={dateRange.start}
                onChange={(e) => setDateRange({ ...dateRange, start: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-[#9d8fbb]"
              />
            </div>
            <div>
              <label className="block text-xs text-gray-500 mb-1">Hasta</label>
              <input
                type="date"
                value={dateRange.end}
                onChange={(e) => setDateRange({ ...dateRange, end: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-[#9d8fbb]"
              />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchFilters;