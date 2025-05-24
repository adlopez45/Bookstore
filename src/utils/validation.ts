import { z } from 'zod';

export const loginSchema = z.object({
  email: z.string().email('Invalid email address'),
  password: z.string().min(6, 'Password must be at least 6 characters'),
});

export const bookSchema = z.object({
  title: z.string().min(1, 'Title is required'),
  author: z.string().min(1, 'Author is required'),
  isbn: z.string().min(10, 'ISBN must be at least 10 characters'),
  price: z.number().positive('Price must be positive'),
});

export type LoginFormData = z.infer<typeof loginSchema>;
export type BookFormData = z.infer<typeof bookSchema>;