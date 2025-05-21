export enum BookCategory {
  Fiction = '2',
  NonFiction = '3',
  ScienceFiction = '4',
  Fantasy = '5',
  Biography = '6',
  Poetry = '7',
  History = '8',
  Romance = '9',
  Mystery = '10'
}

export enum PriceRange {
  Under50k = "Under 50.000 COP",
  From50kTo100k = "50.000 - 100.000 COP",
  From100kTo150k = "100.000 - 150.000 COP",
  Over150k = "Over 150.000 COP"
}

export interface Book {
  id: string;
  title: string;
  author: string;
  description: string;
  coverImage: string;
  price: string;
  numericPrice: number;
  isbn: string;
  category: BookCategory;
  publishDate: string;
}