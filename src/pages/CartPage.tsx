import React from 'react';
import { Trash2 } from 'lucide-react';
import { Link, useNavigate } from 'react-router-dom';
import { useCart } from '../contexts/CartContext';

const CartPage: React.FC = () => {
  const navigate = useNavigate();
  const { items, removeFromCart, updateQuantity } = useCart();
  
  const subtotal = items.reduce((sum, item) => {
    return sum + (item.book.numericPrice * item.quantity);
  }, 0);
  
  const shippingCost = 10000;
  const total = subtotal + shippingCost;

  const handleCheckout = () => {
    navigate('/checkout');
  };
  
  if (items.length === 0) {
    return (
      <div className="page-transition flex flex-col items-center justify-center py-12">
        <h1 className="text-3xl font-bold mb-4">Your Cart is Empty</h1>
        <p className="text-gray-600 mb-8">Add some books to get started</p>
        <Link to="/shop" className="btn-primary">
          Browse Books
        </Link>
      </div>
    );
  }
  
  return (
    <div className="page-transition">
      <h1 className="text-3xl font-bold mb-8">Shopping Cart</h1>
      
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        <div className="lg:col-span-2">
          <div className="bg-white rounded-lg shadow-md overflow-hidden">
            <ul className="divide-y divide-gray-200">
              {items.map((item) => (
                <li key={item.book.id} className="p-4 sm:p-6">
                  <div className="flex flex-col sm:flex-row">
                    <div className="sm:w-24 sm:h-36 mb-4 sm:mb-0">
                      <img 
                        src={item.book.coverImage} 
                        alt={item.book.title} 
                        className="w-full h-full object-cover rounded"
                      />
                    </div>
                    <div className="flex-1 sm:ml-6">
                      <div className="flex justify-between">
                        <div>
                          <h3 className="text-lg font-medium text-gray-800">
                            <Link to={`/book/${item.book.id}`} className="hover:text-[#7c6a9a]">
                              {item.book.title}
                            </Link>
                          </h3>
                          <p className="mt-1 text-sm text-gray-600">{item.book.author}</p>
                        </div>
                        <p className="text-lg font-semibold">{item.book.price}</p>
                      </div>
                      
                      <div className="mt-4 flex justify-between items-center">
                        <div className="flex items-center border border-gray-300 rounded">
                          <button 
                            onClick={() => updateQuantity(item.book.id, item.quantity - 1)}
                            className="px-3 py-1 border-r border-gray-300 text-gray-600 hover:bg-gray-100"
                          >
                            -
                          </button>
                          <span className="px-4 py-1">{item.quantity}</span>
                          <button 
                            onClick={() => updateQuantity(item.book.id, item.quantity + 1)}
                            className="px-3 py-1 border-l border-gray-300 text-gray-600 hover:bg-gray-100"
                          >
                            +
                          </button>
                        </div>
                        <button 
                          onClick={() => removeFromCart(item.book.id)}
                          className="text-gray-500 hover:text-red-500 transition-colors"
                        >
                          <Trash2 size={20} />
                        </button>
                      </div>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>
        </div>
        
        <div className="lg:col-span-1">
          <div className="bg-white rounded-lg shadow-md p-6">
            <h2 className="text-lg font-semibold mb-6 pb-4 border-b">Order Summary</h2>
            
            <div className="space-y-4 mb-6">
              <div className="flex justify-between">
                <span className="text-gray-600">Subtotal</span>
                <span>{subtotal.toLocaleString()}COP</span>
              </div>
              <div className="flex justify-between">
                <span className="text-gray-600">Shipping</span>
                <span>{shippingCost.toLocaleString()}COP</span>
              </div>
              <div className="border-t pt-4 mt-4 flex justify-between font-semibold text-lg">
                <span>Total</span>
                <span>{total.toLocaleString()}COP</span>
              </div>
            </div>
            
            <button 
              onClick={handleCheckout}
              className="w-full btn-primary py-3 font-medium"
            >
              Proceed to Checkout
            </button>
            
            <Link 
              to="/shop" 
              className="mt-4 block text-center text-[#7c6a9a] hover:underline"
            >
              Continue Shopping
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default CartPage;