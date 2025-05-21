import React from 'react';
import { User, CreditCard, ShoppingBag, BookOpen, LogOut } from 'lucide-react';

const AccountPage: React.FC = () => {
  // Mock membership card data
  const membershipCard = {
    number: '**** **** **** 1234',
    balance: 50,
    discountPercentage: 10
  };

  return (
    <div className="page-transition">
      <h1 className="text-3xl font-bold mb-8">My Account</h1>
      
      <div className="grid grid-cols-1 gap-6 lg:grid-cols-3">
        <div className="lg:col-span-1">
          <div className="bg-white rounded-lg shadow-md p-6">
            <div className="flex flex-col items-center">
              <div className="w-32 h-32 rounded-full overflow-hidden mb-4">
                <img 
                  src="https://images.pexels.com/photos/774909/pexels-photo-774909.jpeg" 
                  alt="User profile" 
                  className="w-full h-full object-cover"
                />
              </div>
              <h2 className="text-xl font-semibold">Jane Doe</h2>
              <p className="text-gray-600">jane.doe@example.com</p>
              <button className="mt-4 text-sm text-[#7c6a9a] hover:underline">
                Change Profile Picture
              </button>
            </div>
            
            <div className="mt-8">
              <h3 className="text-lg font-medium mb-3">Account</h3>
              <ul className="space-y-2">
                <li>
                  <button className="w-full flex items-center p-2 rounded hover:bg-gray-100 transition-colors">
                    <User size={18} className="mr-3 text-gray-500" />
                    <span>Personal Information</span>
                  </button>
                </li>
                <li>
                  <button className="w-full flex items-center p-2 rounded hover:bg-gray-100 transition-colors">
                    <CreditCard size={18} className="mr-3 text-gray-500" />
                    <span>Membership Card</span>
                  </button>
                </li>
                <li>
                  <button className="w-full flex items-center p-2 rounded hover:bg-gray-100 transition-colors">
                    <ShoppingBag size={18} className="mr-3 text-gray-500" />
                    <span>Order History</span>
                  </button>
                </li>
                <li>
                  <button className="w-full flex items-center p-2 rounded hover:bg-gray-100 transition-colors">
                    <BookOpen size={18} className="mr-3 text-gray-500" />
                    <span>My Books</span>
                  </button>
                </li>
              </ul>
            </div>
            
            <div className="mt-8">
              <div className="bg-gradient-to-r from-[#7c6a9a] to-[#9d8fbb] text-white rounded-lg p-6">
                <div className="flex items-center mb-4">
                  <CreditCard className="mr-2" />
                  <span className="font-medium">Library Card</span>
                </div>
                <div className="space-y-2">
                  <p className="font-mono">{membershipCard.number}</p>
                  <p className="text-sm">Available Balance: {membershipCard.balance.toLocaleString()}COP</p>
                  <p className="text-sm">Member Discount: {membershipCard.discountPercentage}%</p>
                </div>
              </div>
            </div>
            
            <div className="mt-8 pt-6 border-t">
              <button className="w-full flex items-center p-2 text-red-600 rounded hover:bg-red-50 transition-colors">
                <LogOut size={18} className="mr-3" />
                <span>Log Out</span>
              </button>
            </div>
          </div>
        </div>
        
        {/* Rest of the AccountPage component remains unchanged */}
      </div>
    </div>
  );
};

export default AccountPage;