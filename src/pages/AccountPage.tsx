import React, { useState } from 'react';
import { User, CreditCard, ShoppingBag, BookOpen, LogOut, Plus } from 'lucide-react';
import { useNavigate } from 'react-router-dom';

const AccountPage: React.FC = () => {
  const navigate = useNavigate();
  const [showRecharge, setShowRecharge] = useState(false);
  const [rechargeAmount, setRechargeAmount] = useState('');
  
  // Mock membership card data
  const [membershipCard, setMembershipCard] = useState({
    number: '**** **** **** 1234',
    balance: 50,
    discountPercentage: 10
  });

  const handleLogout = () => {
    localStorage.removeItem('isAuthenticated');
    navigate('/login');
  };

  const handleRecharge = () => {
    const amount = parseInt(rechargeAmount);
    if (!amount || amount <= 0) {
      alert('Please enter a valid amount');
      return;
    }
    
    setMembershipCard(prev => ({
      ...prev,
      balance: prev.balance + amount
    }));
    setRechargeAmount('');
    setShowRecharge(false);
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
                  <button 
                    onClick={() => setShowRecharge(prev => !prev)}
                    className="w-full flex items-center p-2 rounded hover:bg-gray-100 transition-colors"
                  >
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
                <div className="flex items-center justify-between mb-4">
                  <div className="flex items-center">
                    <CreditCard className="mr-2" />
                    <span className="font-medium">Library Card</span>
                  </div>
                  <button 
                    onClick={() => setShowRecharge(prev => !prev)}
                    className="bg-white/20 hover:bg-white/30 rounded-full p-1.5 transition-colors"
                  >
                    <Plus size={18} />
                  </button>
                </div>
                <div className="space-y-2">
                  <p className="font-mono">{membershipCard.number}</p>
                  <p className="text-lg font-semibold">
                    {membershipCard.balance.toLocaleString()}COP
                  </p>
                  <p className="text-sm opacity-80">Member Discount: {membershipCard.discountPercentage}%</p>
                </div>
              </div>

              {showRecharge && (
                <div className="mt-4 p-4 bg-gray-50 rounded-lg border border-gray-200">
                  <h4 className="font-medium mb-3">Recharge Card</h4>
                  <div className="space-y-3">
                    <input
                      type="number"
                      value={rechargeAmount}
                      onChange={(e) => setRechargeAmount(e.target.value)}
                      placeholder="Enter amount in COP"
                      className="w-full p-2 border border-gray-300 rounded focus:ring-2 focus:ring-[#9d8fbb] focus:border-transparent"
                    />
                    <button
                      onClick={handleRecharge}
                      className="w-full bg-[#7c6a9a] text-white py-2 rounded hover:bg-[#6b5889] transition-colors"
                    >
                      Recharge
                    </button>
                  </div>
                </div>
              )}
            </div>
            
            <div className="mt-8 pt-6 border-t">
              <button 
                onClick={handleLogout}
                className="w-full flex items-center p-2 text-red-600 rounded hover:bg-red-50 transition-colors"
              >
                <LogOut size={18} className="mr-3" />
                <span>Log Out</span>
              </button>
            </div>
          </div>
        </div>
        
        <div className="lg:col-span-2">
          <div className="bg-white rounded-lg shadow-md p-6">
            <h2 className="text-xl font-semibold mb-6 pb-2 border-b">Personal Information</h2>
            
            <form className="space-y-6">
              <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">
                    First Name
                  </label>
                  <input
                    type="text"
                    defaultValue="Jane"
                    className="input-field"
                  />
                </div>
                <div>
                  <label className="block text-sm font-medium text-gray-700 mb-1">
                    Last Name
                  </label>
                  <input
                    type="text"
                    defaultValue="Doe"
                    className="input-field"
                  />
                </div>
              </div>
              
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Email
                </label>
                <input
                  type="email"
                  defaultValue="jane.doe@example.com"
                  className="input-field"
                />
              </div>
              
              <div>
                <label className="block text-sm font-medium text-gray-700 mb-1">
                  Phone
                </label>
                <input
                  type="tel"
                  defaultValue="+1 234 567 8901"
                  className="input-field"
                />
              </div>
              
              <div>
                <h3 className="text-lg font-medium mb-3">Address</h3>
                <div className="space-y-4">
                  <div>
                    <label className="block text-sm font-medium text-gray-700 mb-1">
                      Street Address
                    </label>
                    <input
                      type="text"
                      defaultValue="123 Book Lane"
                      className="input-field"
                    />
                  </div>
                  
                  <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        City
                      </label>
                      <input
                        type="text"
                        defaultValue="Booksville"
                        className="input-field"
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        State
                      </label>
                      <input
                        type="text"
                        defaultValue="California"
                        className="input-field"
                      />
                    </div>
                    <div>
                      <label className="block text-sm font-medium text-gray-700 mb-1">
                        Zip Code
                      </label>
                      <input
                        type="text"
                        defaultValue="90210"
                        className="input-field"
                      />
                    </div>
                  </div>
                </div>
              </div>
              
              <div className="flex justify-end">
                <button
                  type="button"
                  className="btn-primary px-6"
                >
                  Save Changes
                </button>
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  );
};

export default AccountPage;