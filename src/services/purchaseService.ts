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
// Basado en el PurchaseController.java que devuelve Map.of("message", ..., "purchaseId", ...)
// o Map.of("error", ...)
export interface ApiPurchaseResponse {
    message?: string; // Mensaje de éxito
    purchaseId?: number; // ID de la compra si fue exitosa
    error?: string;      // Mensaje de error si falló
}

const API_BASE_URL = '/api'; // Usamos la ruta relativa gracias al proxy de Vite

export const makePurchaseApi = async (purchaseData: ApiPurchaseRequest): Promise<ApiPurchaseResponse> => {
    const response = await fetch(`${API_BASE_URL}/purchases/make`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(purchaseData),
    });

    // Es importante intentar parsear el cuerpo de la respuesta SIEMPRE,
    // porque incluso en errores, el backend puede enviar un JSON con el mensaje.
    let responseBody: ApiPurchaseResponse;
    try {
        responseBody = await response.json();
    } catch (e) {
        // Si el cuerpo no es JSON válido (ej. respuesta de error inesperada del servidor sin JSON)
        const textError = await response.text(); // Intenta obtener el texto del error
        throw new Error(textError || `Error en la conexión: ${response.status} ${response.statusText}`);
    }

    if (!response.ok) {
        // responseBody.error debería contener el mensaje del backend si fue un error controlado (400, 409)
        // responseBody.message también podría usarse si el backend lo envía en errores.
        throw new Error(responseBody.error || responseBody.message || `Error en la compra: ${response.status}`);
    }

    // Si la respuesta es OK (ej. 201 Created), responseBody debería tener "message" y "purchaseId"
    return responseBody;
};