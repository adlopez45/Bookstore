import React from 'react';
import { Package, ChevronRight } from 'lucide-react';

interface Order {
  id: string;
  date: string;
  status: 'pending' | 'processing' | 'delivered' | 'cancelled';
  total: string;
  items: {
    title: string;
    quantity: number;
    price: string;
  }[];
}

const mockOrders: Order[] = [
  {
    id: 'ORD-001',
    date: '2025-06-25',
    status: 'delivered',
    total: '280.000COP',
    items: [
      { title: 'One Hundred Years of Solitude', quantity: 1, price: '130.000COP' },
      { title: 'Satanas', quantity: 1, price: '110.000COP' }
    ]
  },
  {
    id: 'ORD-002',
    date: '2025-06-12',
    status: 'processing',
    total: '130.000COP',
    items: [
      { title: 'The Fault in Our Stars', quantity: 1, price: '95.000COP' }
    ]
  }
];

const OrdersPage: React.FC = () => {
  const getStatusColor = (status: Order['status']) => {
    switch (status) {
      case 'delivered':
        return 'bg-green-100 text-green-800';
      case 'processing':
        return 'bg-blue-100 text-blue-800';
      case 'pending':
        return 'bg-yellow-100 text-yellow-800';
      case 'cancelled':
        return 'bg-red-100 text-red-800';
      default:
        return 'bg-gray-100 text-gray-800';
    }
  };

  return (
    <div className="page-transition">
      <h1 className="text-3xl font-bold mb-8">My Orders</h1>
      
      <div className="space-y-6">
        {mockOrders.map((order) => (
          <div key={order.id} className="bg-white rounded-lg shadow-md overflow-hidden">
            <div className="p-6 border-b">
              <div className="flex items-center justify-between mb-4">
                <div>
                  <h2 className="text-lg font-semibold">Order #{order.id}</h2>
                  <p className="text-sm text-gray-500">
                    Placed on {new Date(order.date).toLocaleDateString()}
                  </p>
                </div>
                <span className={`px-3 py-1 rounded-full text-xs font-semibold ${getStatusColor(order.status)}`}>
                  {order.status.charAt(0).toUpperCase() + order.status.slice(1)}
                </span>
              </div>
              
              <div className="space-y-4">
                {order.items.map((item, index) => (
                  <div key={index} className="flex items-center justify-between">
                    <div className="flex items-center space-x-4">
                      <Package className="w-8 h-8 text-gray-400" />
                      <div>
                        <h3 className="font-medium">{item.title}</h3>
                        <p className="text-sm text-gray-500">Quantity: {item.quantity}</p>
                      </div>
                    </div>
                    <span className="font-medium">{item.price}</span>
                  </div>
                ))}
              </div>
            </div>
            
            <div className="p-6 bg-gray-50 flex items-center justify-between">
              <div>
                <span className="text-sm text-gray-500">Total Amount</span>
                <p className="text-lg font-semibold">{order.total}</p>
              </div>
              <button className="flex items-center text-[#7c6a9a] hover:text-[#9d8fbb] transition-colors">
                <span className="mr-2">View Details</span>
                <ChevronRight size={20} />
              </button>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default OrdersPage;