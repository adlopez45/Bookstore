import React from 'react';
import { Link } from 'react-router-dom';
import { ShoppingCart } from 'lucide-react';
import SearchBar from './SearchBar';
import { useCart } from '../contexts/CartContext';

const Navbar: React.FC = () => {
  const { items } = useCart();
  const totalItems = items.reduce((sum, item) => sum + item.quantity, 0);

  return (
    <nav className="bg-gray-200 shadow-sm">
      <div className="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        <div className="flex justify-between h-16 items-center">
          <div className="flex-1 flex items-center justify-center sm:justify-start">
            <SearchBar />
          </div>
          <div className="flex items-center space-x-8">
            <Link 
              to="/shop" 
              className="text-gray-900 hover:text-[#7c6a9a] transition-colors font-medium"
            >
              Shop
            </Link>
            <Link 
              to="/cart" 
              className="text-gray-900 hover:text-[#7c6a9a] transition-colors flex items-center space-x-1"
            >
              <div className="relative">
                <ShoppingCart className="h-5 w-5" />
                {totalItems > 0 && (
                  <span className="absolute -top-2 -right-2 bg-[#7c6a9a] text-white text-xs rounded-full w-4 h-4 flex items-center justify-center">
                    {totalItems}
                  </span>
                )}
              </div>
              <span className="font-medium">Shopping cart</span>
            </Link>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;