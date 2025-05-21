import React, { useState, useEffect } from 'react';
import { Search } from 'lucide-react';
import { useNavigate, useLocation } from 'react-router-dom';

const SearchBar: React.FC = () => {
  const navigate = useNavigate();
  const location = useLocation();
  const [searchQuery, setSearchQuery] = useState('');

  // Get search query from URL on component mount
  useEffect(() => {
    const params = new URLSearchParams(location.search);
    const query = params.get('q') || '';
    setSearchQuery(query);
  }, [location.search]);

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (searchQuery.trim()) {
      navigate(`/shop?q=${encodeURIComponent(searchQuery.trim())}`);
    } else {
      navigate('/shop');
    }
  };

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const value = e.target.value;
    setSearchQuery(value);
    
    // Only update URL if we're already on the shop page
    if (location.pathname === '/shop') {
      if (value.trim()) {
        navigate(`/shop?q=${encodeURIComponent(value.trim())}`);
      } else {
        navigate('/shop');
      }
    }
  };

  return (
    <form onSubmit={handleSubmit} className="relative w-full max-w-xl">
      <div className="absolute inset-y-0 left-0 pl-3 flex items-center pointer-events-none">
        <Search className="h-5 w-5 text-[#7c6a9a]" />
      </div>
      <input
        type="text"
        placeholder="Search by title, author, or ISBN..."
        className="w-full pl-10 pr-4 py-2.5 rounded-full border-2 border-[#9d8fbb] bg-white/90 backdrop-blur-sm focus:ring-2 focus:ring-[#7c6a9a] focus:border-[#7c6a9a] text-sm transition-all duration-200"
        value={searchQuery}
        onChange={handleChange}
      />
    </form>
  );
};

export default SearchBar;