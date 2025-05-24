// project/src/services/purchaseService.ts

// Estructura para el request de compra, debe coincidir con PurchaseRequest.java
interface BookPurchaseClient {
    bookId: number; // El ID numérico del libro
    quantity: number;
}

export interface ApiPurchaseRequest { // Lo que envías al backend
    userId: number;
    cardId: number;
    books: BookPurchaseClient[];
}

// Estructura para la respuesta esperada del endpoint /api/purchases/make
export interface ApiPurchaseResponse {
    message?: string;
    purchaseId?: number;
    error?: string;
}

const API_BASE_URL = '/api';

export const makePurchaseApi = async (purchaseData: ApiPurchaseRequest): Promise<ApiPurchaseResponse> => {
    try {
        const response = await fetch(`${API_BASE_URL}/purchases/make`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(purchaseData),
        });

        const responseData = await response.json();

        if (!response.ok) {
            throw new Error(responseData.error || responseData.message || `Error en la compra: ${response.status}`);
        }

        return responseData;
    } catch (error) {
        if (error instanceof Error) {
            // Si el error es sobre la tarjeta no encontrada, personalizar el mensaje
            if (error.message.includes('Tarjeta no encontrada')) {
                throw new Error('Por favor seleccione una tarjeta de membresía válida para realizar la compra.');
            }
            throw error;
        }
        throw new Error('Error inesperado durante la compra');
    }
};