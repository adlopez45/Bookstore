interface CartItem {
  bookId: string;
  title: string;
  author: string;
  coverImage: string;
  price: string;
  quantity: number;
}

export const mockCartItems: CartItem[] = [
  {
    bookId: '1',
    title: 'One Hundred Years of Solitude',
    author: 'Gabriel García Márquez',
    coverImage: 'https://images.penguinrandomhouse.com/cover/9780525562443',
    price: '130.000COP',
    quantity: 1
  },
  {
    bookId: '3',
    title: 'Satanas',
    author: 'Mario Mendoza',
    coverImage: 'https://images-na.ssl-images-amazon.com/images/S/compressed.photo.goodreads.com/books/1347709934i/1200088.jpg',
    price: '110.000COP',
    quantity: 1
  }
];