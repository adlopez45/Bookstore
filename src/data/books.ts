import { Book, BookCategory } from '../types/Book';
import * as assets from '../assets';

export const favoritesBooks: Book[] = [
  {
    id: '1',
    title: 'One Hundred Years of Solitude',
    author: 'Gabriel García Márquez',
    description: 'The multi-generational story of the Buendía family, whose patriarch, José Arcadio Buendía, founded the town of Macondo, the metaphoric Colombia.',
    coverImage: assets.gabrielGarciaMarquez,
    price: '130.000COP',
    numericPrice: 130000,
    isbn: '111111',
    category: BookCategory.Fiction,
    publishDate: '1967-06-05'
  },
  {
    id: '2',
    title: 'The Fault in Our Stars',
    author: 'John Green',
    description: 'A novel about a young teenage girl who has been diagnosed with lung cancer and attends a cancer support group.',
    coverImage: assets.bajoLaMismaEstrella,
    price: '95.000COP',
    numericPrice: 95000,
    isbn: '222222',
    category: BookCategory.Fiction,
    publishDate: '2012-01-10'
  },
  {
    id: '3',
    title: 'Satanas',
    author: 'Mario Mendoza',
    description: 'A gripping story that explores the darker aspects of human nature and society.',
    coverImage: assets.satanas,
    price: '110.000COP',
    numericPrice: 110000,
    isbn: '333333',
    category: BookCategory.Mystery,
    publishDate: '2002-05-15'
  },
  {
    id: '4',
    title: 'Miss Kim Knows',
    author: 'Nam-Joo Cho',
    description: 'A thought-provoking novel that addresses social issues and personal development.',
    coverImage: assets.missKinKnows,
    price: '125.000COP',
    numericPrice: 125000,
    isbn: '444444',
    category: BookCategory.Fiction,
    publishDate: '2021-03-22'
  }
];

export const newBooks: Book[] = [
  {
    id: '5',
    title: '1984',
    author: 'George Orwell',
    description: 'A dystopian novel set in a totalitarian regime where critical thought is suppressed.',
    coverImage: assets._1984,
    price: '105.000COP',
    numericPrice: 105000,
    isbn: '555555',
    category: BookCategory.ScienceFiction,
    publishDate: '1949-06-08'
  },
  {
    id: '6',
    title: 'Crescent City',
    author: 'Sarah J. Maas',
    description: 'A tale of love, friendship, and searching for light in dark times.',
    coverImage: assets.crimenYCastigo,
    price: '145.000COP',
    numericPrice: 145000,
    isbn: '666666',
    category: BookCategory.Fantasy,
    publishDate: '2020-03-03'
  },
  {
    id: '7',
    title: 'The Little Prince',
    author: 'Antoine de Saint-Exupéry',
    description: 'A poetic tale about a young prince who visits various planets in space, including Earth.',
    coverImage: assets.elPrincipito,
    price: '85.000COP',
    numericPrice: 85000,
    isbn: '777777',
    category: BookCategory.Fiction,
    publishDate: '1943-04-06'
  },
  {
    id: '8',
    title: 'Until the Kingdom Comes',
    author: 'Connor Hamilton',
    description: 'An epic fantasy adventure set in a world of magic and mystery.',
    coverImage: assets.hastaQueElVientoTeDevuelvaLaSonrisa,
    price: '165.000COP',
    numericPrice: 165000,
    isbn: '888888',
    category: BookCategory.Fantasy,
    publishDate: '2023-11-14'
  }
];

export const allBooks: Book[] = [
  ...favoritesBooks,
  ...newBooks,
  {
    id: '9',
    title: 'Pride and Prejudice',
    author: 'Jane Austen',
    description: 'A romantic novel of manners that follows the character development of Elizabeth Bennet.',
    coverImage: assets.prideAndPrejudice,
    price: '90.000COP',
    numericPrice: 90000,
    isbn: '999999',
    category: BookCategory.Romance,
    publishDate: '1813-01-28'
  },
  {
    id: '10',
    title: 'To Kill a Mockingbird',
    author: 'Harper Lee',
    description: 'A story about racial inequality and moral growth, told through the eyes of a young girl.',
    coverImage: assets.toKillAMockingbird,
    price: '115.000COP',
    numericPrice: 115000,
    isbn: '101010',
    category: BookCategory.Fiction,
    publishDate: '1960-07-11'
  },
  {
    id: '11',
    title: 'Sapiens: A Brief History of Humankind',
    author: 'Yuval Noah Harari',
    description: 'A book that explores the history and impact of Homo sapiens on the world.',
    coverImage: assets.aBriefHistoryOfHumankind,
    price: '135.000COP',
    numericPrice: 135000,
    isbn: '111011',
    category: BookCategory.NonFiction,
    publishDate: '2011-02-10'
  },
  {
    id: '12',
    title: 'The Great Gatsby',
    author: 'F. Scott Fitzgerald',
    description: 'A novel that examines themes of decadence, idealism, social upheaval, and excess.',
    coverImage: assets.theGreatGatsby,
    price: '100.000COP',
    numericPrice: 100000,
    isbn: '121212',
    category: BookCategory.Fiction,
    publishDate: '1925-04-10'
  }
];