import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { XCircle, RefreshCw, CreditCard } from 'lucide-react';

const OrderDeniedPage: React.FC = () => {
  const navigate = useNavigate();
  
  const handleRecharge = () => {
    navigate('/account');
  };

  return (
    <div className="page-transition min-h-[80vh] flex items-center justify-center">
      <div className="bg-white p-8 rounded-lg shadow-lg max-w-md w-full text-center">
        <div className="mb-6 flex justify-center">
          <div className="w-16 h-16 bg-red-100 rounded-full flex items-center justify-center">
            <XCircle className="w-10 h-10 text-red-500" />
          </div>
        </div>
        
        <h1 className="text-2xl font-bold text-gray-800 mb-4">Order Denied</h1>
        
        <p className="text-gray-600 mb-8">
          We couldn't process your order due to insufficient balance on your membership card.
          Please recharge your card and try again.
        </p>
        
        <div className="border-t border-gray-200 pt-6">
          <div className="flex flex-col space-y-4">
            <button
              onClick={handleRecharge}
              className="btn-primary flex items-center justify-center space-x-2 w-full"
            >
              <CreditCard size={20} />
              <span>Recharge Card</span>
            </button>
            
            <Link 
              to="/cart" 
              className="btn-secondary flex items-center justify-center space-x-2"
            >
              <RefreshCw size={20} />
              <span>Return to Cart</span>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderDeniedPage;