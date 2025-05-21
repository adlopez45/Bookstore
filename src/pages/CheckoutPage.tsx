// project/src/pages/CheckoutPage.tsx
import React, { useState, useEffect } from 'react'; // useEffect si necesitas cargar datos de tarjeta/usuario
import { useNavigate } from 'react-router-dom';
import { useCart } from '../contexts/CartContext';
import { CreditCard, AlertCircle, ShoppingBag } from 'lucide-react'; // ShoppingBag añadido
import { makePurchaseApi, ApiPurchaseRequest, ApiPurchaseResponse } from '../services/purchaseService'; // Importar servicio

const CheckoutPage: React.FC = () => {
  const navigate = useNavigate();
  const { items, total, clearCart } = useCart();

  // --- SIMULACIÓN DE DATOS DE USUARIO Y TARJETA ---
  // En una aplicación real, estos vendrían de tu AuthContext o una llamada a API
  const MOCK_USER_ID = 1; // REEMPLAZA con un ID de usuario válido de tu BD
  const MOCK_CARD_ID = 1; // REEMPLAZA con un ID de tarjeta válido para ese usuario

  // Simulación de datos de la tarjeta (el saldo real está en el backend)
  // Podrías hacer una llamada a la API para obtener el saldo si quieres mostrarlo actualizado.
  const [membershipCardInfo, setMembershipCardInfo] = useState({
    number: '**** **** **** 1234', // Número de tarjeta mock
    balance: 200000, // Saldo mock, el backend lo validará realmente
    discountPercentage: 10,
  });
  // --- FIN DE SIMULACIÓN ---

  const [isPurchasing, setIsPurchasing] = useState(false);
  const [purchaseError, setPurchaseError] = useState<string | null>(null);

  const discountAmount = (total * membershipCardInfo.discountPercentage) / 100;
  const finalAmount = total - discountAmount;

  // Opcional: Cargar datos de la tarjeta del usuario al montar la página
  // useEffect(() => {
  //   const fetchCardData = async () => {
  //     try {
  //       // const cardData = await fetchCardDetailsForUser(MOCK_USER_ID, MOCK_CARD_ID); // Necesitarías este servicio
  //       // setMembershipCardInfo(cardData);
  //     } catch (error) {
  //       console.error("Error fetching card details:", error);
  //       setPurchaseError("No se pudieron cargar los detalles de la tarjeta.");
  //     }
  //   };
  //   fetchCardData();
  // }, []);


  const handlePurchase = async () => {
    if (items.length === 0) {
      setPurchaseError("Tu carrito está vacío.");
      return;
    }

    setIsPurchasing(true);
    setPurchaseError(null);

    const purchaseRequestData: ApiPurchaseRequest = {
      userId: MOCK_USER_ID,
      cardId: MOCK_CARD_ID,
      books: items.map(item => ({
        // `item.book.id` es string por tu tipo `Book` del frontend. El backend espera `Integer`.
        bookId: parseInt(item.book.id, 10),
        quantity: item.quantity,
      })),
    };

    try {
      const response: ApiPurchaseResponse = await makePurchaseApi(purchaseRequestData);
      console.log('Compra exitosa:', response.message, 'ID de Compra:', response.purchaseId);
      clearCart();
      navigate('/order-approved'); // Navega a la página de éxito
    } catch (error: any) {
      console.error('Error al procesar la compra:', error);
      setPurchaseError(error.message || 'Ocurrió un error desconocido durante la compra.');
      // Podrías navegar a /order-denied si el error es específico de saldo/stock
      if (error.message && (error.message.toLowerCase().includes("saldo insuficiente") || error.message.toLowerCase().includes("stock insuficiente"))) {
         navigate('/order-denied');
      }
    } finally {
      setIsPurchasing(false);
    }
  };

  // Simulación de recarga, en una app real llamaría a una API
  const handleRecharge = () => {
    // setMembershipCardInfo(prev => ({ ...prev, balance: prev.balance + 50000 }));
    // alert("Simulación de recarga: +50.000COP. Refresca para ver cambios si el saldo es de API.");
    // O mejor, navegar a la página de cuenta para recargar de verdad
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

                {/* Advertencia de saldo insuficiente (basado en saldo mock, el backend hará la validación real) */}
                {membershipCardInfo.balance < finalAmount && (
                  <div className="mt-3 bg-red-500/20 p-3 rounded-md flex items-start space-x-2 text-red-100 text-sm">
                    <AlertCircle className="w-5 h-5 flex-shrink-0 mt-0.5" />
                    <div>
                      <p className="font-medium">Saldo Insuficiente Simulado</p>
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
                disabled={isPurchasing || items.length === 0}
                className="w-full btn-primary py-3 font-medium flex items-center justify-center gap-2"
              >
                <ShoppingBag size={20} />
                {isPurchasing ? "Procesando Compra..." : "Confirmar y Pagar"}
              </button>
              {membershipCardInfo.balance < finalAmount && (
                 <button
                    onClick={handleRecharge}
                    className="w-full btn-secondary py-3 font-medium flex items-center justify-center gap-2"
                    disabled={isPurchasing}
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