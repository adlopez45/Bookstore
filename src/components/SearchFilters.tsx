import React from 'react';
import { BookCategory, PriceRange, priceRanges } from '../types/Book';

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
      <h2 className="text-lg font-semibold mb-4">Filters</h2>
      
      <div className="space-y-6">
        {/* Category Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Categories</h3>
          <div className="flex flex-wrap gap-2">
            <button
              onClick={() => setSelectedCategory('all')}
              className={`px-3 py-1 rounded-full text-sm transition-colors ${
                selectedCategory === 'all'
                  ? 'bg-[#7c6a9a] text-white'
                  : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
              }`}
            >
              All
            </button>
            {Object.values(BookCategory).map((category) => (
              <button
                key={category}
                onClick={() => setSelectedCategory(category)}
                className={`px-3 py-1 rounded-full text-sm transition-colors ${
                  selectedCategory === category
                    ? 'bg-[#7c6a9a] text-white'
                    : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                }`}
              >
                {category}
              </button>
            ))}
          </div>
        </div>

        {/* Price Range Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Price Range</h3>
          <div className="space-y-2">
            <button
              onClick={() => setSelectedPriceRange(null)}
              className={`w-full text-left px-3 py-2 rounded text-sm transition-colors ${
                !selectedPriceRange
                  ? 'bg-[#7c6a9a] text-white'
                  : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
              }`}
            >
              All Prices
            </button>
            {priceRanges.map((range, index) => (
              <button
                key={index}
                onClick={() => setSelectedPriceRange(range)}
                className={`w-full text-left px-3 py-2 rounded text-sm transition-colors ${
                  selectedPriceRange === range
                    ? 'bg-[#7c6a9a] text-white'
                    : 'bg-gray-200 text-gray-700 hover:bg-gray-300'
                }`}
              >
                {range.label}
              </button>
            ))}
          </div>
        </div>

        {/* Author Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Author</h3>
          <select
            value={selectedAuthor}
            onChange={(e) => setSelectedAuthor(e.target.value)}
            className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-[#9d8fbb]"
          >
            <option value="">All Authors</option>
            {authors.map((author) => (
              <option key={author} value={author}>
                {author}
              </option>
            ))}
          </select>
        </div>

        {/* Publication Date Filter */}
        <div>
          <h3 className="text-sm font-medium text-gray-700 mb-2">Publication Date</h3>
          <div className="grid grid-cols-2 gap-4">
            <div>
              <label className="block text-xs text-gray-500 mb-1">From</label>
              <input
                type="date"
                value={dateRange.start}
                onChange={(e) => setDateRange({ ...dateRange, start: e.target.value })}
                className="w-full p-2 border border-gray-300 rounded-md focus:outline-none focus:ring-2 focus:ring-[#9d8fbb]"
              />
            </div>
            <div>
              <label className="block text-xs text-gray-500 mb-1">To</label>
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