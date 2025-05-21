import React from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { User, ShoppingBag, Settings, Bell, Info, LogOut } from 'lucide-react';
import { useCart } from '../contexts/CartContext';

const Sidebar: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { clearCart } = useCart();
  
  const isActive = (path: string) => {
    return location.pathname === path ? 'active' : '';
  };

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated');
    clearCart();
    navigate('/login');
  };

  return (
    <aside className="w-64 bg-[#9d8fbb] text-white flex flex-col">
      <div className="p-4 flex items-center">
        <div className="text-amber-200">
          <svg width="48" height="48" viewBox="0 0 48 48" fill="none" xmlns="http://www.w3.org/2000/svg">
            <path d="M12 8C10.9 8 10 8.9 10 10V38C10 39.1 10.9 40 12 40H36C37.1 40 38 39.1 38 38V10C38 8.9 37.1 8 36 8H12Z" fill="#FEE191" />
            <path d="M34 8H14V40H34C35.1 40 36 39.1 36 38V10C36 8.9 35.1 8 34 8Z" fill="#FEE191" />
            <path d="M14 8H12C10.9 8 10 8.9 10 10V38C10 39.1 10.9 40 12 40H14V8Z" fill="#F6D477" />
            <path d="M24 8H14V40H24V8Z" fill="#FEE8B8" />
            <path d="M24 8H34V40H24V8Z" fill="#FEE8B8" />
            <path d="M24 38V10" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M18 14H20" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M18 18H20" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M18 22H20" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M18 26H20" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M18 30H20" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M18 34H20" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M28 14H30" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M28 18H30" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M28 22H30" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M28 26H30" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M28 30H30" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M28 34H30" stroke="#BF9D3B" strokeWidth="1.5" strokeLinecap="round" />
            <path d="M14 40C13.5 40 12 40 12 40L14 44H24L26 40H36C36 40 35 40 34 40H14Z" fill="#C31F1F" />
          </svg>
        </div>
        <h1 className="ml-2 text-xl font-bold">Library</h1>
      </div>
      
      <div className="flex flex-col items-center mt-8 mb-8">
        <div className="w-24 h-24 rounded-full bg-gray-300 overflow-hidden mb-2">
          <img 
            src="https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg" 
            alt="User profile" 
            className="w-full h-full object-cover"
          />
        </div>
        <h2 className="font-semibold text-lg">User</h2>
      </div>
      
      <div className="flex-1">
        <nav>
          <ul className="space-y-1 px-2">
            <li>
              <Link 
                to="/account" 
                className={`sidebar-link ${isActive('/account')}`}
              >
                <User size={20} />
                <span>Account</span>
              </Link>
            </li>
            <li>
              <Link 
                to="/orders" 
                className={`sidebar-link ${isActive('/orders')}`}
              >
                <ShoppingBag size={20} />
                <span>My orders</span>
              </Link>
            </li>
            <li>
              <Link 
                to="/settings" 
                className={`sidebar-link ${isActive('/settings')}`}
              >
                <Settings size={20} />
                <span>Settings</span>
              </Link>
            </li>
          </ul>
        </nav>
      </div>
      
      <div className="p-4 border-t border-[#b5a8cc]">
        <ul className="space-y-1">
          <li>
            <Link 
              to="/notifications" 
              className={`sidebar-link ${isActive('/notifications')}`}
            >
              <Bell size={20} />
              <span>Notifications</span>
            </Link>
          </li>
          <li>
            <Link 
              to="/info" 
              className={`sidebar-link ${isActive('/info')}`}
            >
              <Info size={20} />
              <span>Information</span>
            </Link>
          </li>
          <li>
            <button
              onClick={handleLogout}
              className="w-full sidebar-link text-left hover:bg-red-500/20"
            >
              <LogOut size={20} />
              <span>Logout</span>
            </button>
          </li>
        </ul>
      </div>
    </aside>
  );
};

export default Sidebar;