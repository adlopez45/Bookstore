import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { useCart } from '../contexts/CartContext';
import { CreditCard, AlertCircle, ShoppingBag } from 'lucide-react';
import { makePurchaseApi, ApiPurchaseRequest } from '../services/purchaseService';
import { useAuth } from '../contexts/AuthContext';
import { useLoadingState } from '../hooks/useLoadingState';

const CheckoutPage: React.FC = () => {
  const navigate = useNavigate();
  const { items, total, clearCart } = useCart();
  const { user } = useAuth();
  const { isLoading, withLoading } = useLoadingState();

  const [membershipCardInfo, setMembershipCardInfo] = useState({
    id: 1, // Este ID debería venir de la API
    number: '**** **** **** 1234',
    balance: 200000,
    discountPercentage: 10
  });

  const [purchaseError, setPurchaseError] = useState<string | null>(null);

  const discountAmount = (total * membershipCardInfo.discountPercentage) / 100;
  const finalAmount = total - discountAmount;

  const handlePurchase = async () => {
    if (items.length === 0) {
      setPurchaseError("Tu carrito está vacío.");
      return;
    }

    if (!user?.id) {
      setPurchaseError("Debes iniciar sesión para realizar la compra.");
      return;
    }

    setPurchaseError(null);

    const purchaseRequestData: ApiPurchaseRequest = {
      userId: user.id,
      cardId: membershipCardInfo.id,
      books: items.map(item => ({
        bookId: parseInt(item.book.id, 10),
        quantity: item.quantity,
      })),
    };

    try {
      await withLoading(makePurchaseApi(purchaseRequestData));
      clearCart();
      navigate('/order-approved');
    } catch (error) {
      console.error('Error al procesar la compra:', error);
      setPurchaseError(error instanceof Error ? error.message : 'Error desconocido durante la compra');
      
      if (error instanceof Error && 
          (error.message.toLowerCase().includes("saldo insuficiente") || 
           error.message.toLowerCase().includes("tarjeta no encontrada"))) {
        navigate('/order-denied');
      }
    }
  };

  const handleRecharge = () => {
    navigate('/account');
  };

  return (
    <div className="page-transition max-w-2xl mx-auto">
      <div className="bg-white rounded-lg shadow-md p-6 sm:p-8">
        <h1 className="text-2xl sm:text-3xl font-semibold mb-6 text-gray-800">Resumen del Pedido</h1>

        {items.length > 0 ? (
          <>
            <div className="space-y-3 mb-6">
              {items.map(item => (
                <div key={item.book.id} className="flex justify-between items-center text-sm pb-2 border-b border-gray-200 last:border-b-0">
                  <div>
                    <p className="font-medium text-gray-700">{item.book.title} (x{item.quantity})</p>
                    <p className="text-xs text-gray-500">{item.book.author}</p>
                  </div>
                  <p className="text-gray-700">{(item.book.numericPrice * item.quantity).toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}</p>
                </div>
              ))}
            </div>

            <div className="space-y-2 py-4 border-t border-b border-gray-200 mb-6">
              <div className="flex justify-between">
                <span className="text-gray-600">Subtotal:</span>
                <span className="font-medium">{total.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}</span>
              </div>
              <div className="flex justify-between text-green-600">
                <span>Descuento Membresía ({membershipCardInfo.discountPercentage}%):</span>
                <span className="font-medium">-{discountAmount.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}</span>
              </div>
              <div className="flex justify-between font-semibold text-lg text-gray-800 pt-2 border-t border-gray-100">
                <span>Total a Pagar:</span>
                <span>{finalAmount.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}</span>
              </div>
            </div>

            <div className="mb-6">
              <h2 className="text-lg font-medium mb-3 text-gray-700">Método de Pago</h2>
              <div className="bg-gradient-to-r from-[#7c6a9a] to-[#9d8fbb] text-white rounded-lg p-5 shadow">
                <div className="flex items-center justify-between mb-3">
                  <div className="flex items-center">
                    <CreditCard className="mr-2" />
                    <span className="font-medium">Tarjeta de Membresía</span>
                  </div>
                  <span className="text-xs opacity-80">Saldo: {membershipCardInfo.balance.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}</span>
                </div>
                <p className="font-mono text-lg">{membershipCardInfo.number}</p>

                {membershipCardInfo.balance < finalAmount && (
                  <div className="mt-3 bg-red-500/20 p-3 rounded-md flex items-start space-x-2 text-red-100 text-sm">
                    <AlertCircle className="w-5 h-5 flex-shrink-0 mt-0.5" />
                    <div>
                      <p className="font-medium">Saldo Insuficiente</p>
                      <p>Necesitas {(finalAmount - membershipCardInfo.balance).toLocaleString('es-CO', { style: 'currency', currency: 'COP' })} más.</p>
                    </div>
                  </div>
                )}
              </div>
            </div>

            {purchaseError && (
              <div className="my-4 text-sm text-red-600 bg-red-100 p-3 rounded-md text-center">
                {purchaseError}
              </div>
            )}

            <div className="space-y-3">
              <button
                onClick={handlePurchase}
                disabled={isLoading || items.length === 0}
                className="w-full btn-primary py-3 font-medium flex items-center justify-center gap-2 disabled:opacity-50 disabled:cursor-not-allowed"
              >
                <ShoppingBag size={20} />
                {isLoading ? "Procesando Compra..." : "Confirmar y Pagar"}
              </button>
              {membershipCardInfo.balance < finalAmount && (
                <button
                  onClick={handleRecharge}
                  className="w-full btn-secondary py-3 font-medium flex items-center justify-center gap-2"
                  disabled={isLoading}
                >
                  <CreditCard size={20} />
                  Ir a Recargar Tarjeta
                </button>
              )}
            </div>
          </>
        ) : (
          <div className="text-center py-10">
            <p className="text-gray-600 mb-4">Tu carrito está vacío.</p>
            <button onClick={() => navigate('/shop')} className="btn-primary">
              Ir a la tienda
            </button>
          </div>
        )}
      </div>
    </div>
  );
};

export default CheckoutPage;