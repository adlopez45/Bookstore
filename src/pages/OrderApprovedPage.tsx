import React from 'react';
import { Link } from 'react-router-dom';
import { CheckCircle, Home, ShoppingBag } from 'lucide-react';

const OrderApprovedPage: React.FC = () => {
  return (
    <div className="page-transition min-h-[80vh] flex items-center justify-center">
      <div className="bg-white p-8 rounded-lg shadow-lg max-w-md w-full text-center">
        <div className="mb-6 flex justify-center">
          <div className="w-16 h-16 bg-green-100 rounded-full flex items-center justify-center">
            <CheckCircle className="w-10 h-10 text-green-500" />
          </div>
        </div>
        
        <h1 className="text-2xl font-bold text-gray-800 mb-4">Order Approved!</h1>
        
        <p className="text-gray-600 mb-8">
          Your order has been successfully processed. Thank you for shopping with us!
        </p>
        
        <div className="border-t border-gray-200 pt-6">
          <div className="flex flex-col space-y-4">
            <Link 
              to="/orders" 
              className="btn-primary flex items-center justify-center space-x-2"
            >
              <ShoppingBag size={20} />
              <span>View Order</span>
            </Link>
            
            <Link 
              to="/" 
              className="btn-secondary flex items-center justify-center space-x-2"
            >
              <Home size={20} />
              <span>Return to Home</span>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderApprovedPage;