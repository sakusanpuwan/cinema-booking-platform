SELECT setval(pg_get_serial_sequence('public.movies', 'movie_id'),
              COALESCE(MAX(movie_id), 1), MAX(movie_id) IS NOT NULL)
FROM public.movies;

SELECT setval(pg_get_serial_sequence('public.cinemas', 'cinema_id'),
              COALESCE(MAX(cinema_id), 1), MAX(cinema_id) IS NOT NULL)
FROM public.cinemas;

SELECT setval(pg_get_serial_sequence('public.screens', 'screen_id'),
              COALESCE(MAX(screen_id), 1), MAX(screen_id) IS NOT NULL)
FROM public.screens;

SELECT setval(pg_get_serial_sequence('public.seats', 'seat_id'),
              COALESCE(MAX(seat_id), 1), MAX(seat_id) IS NOT NULL)
FROM public.seats;

SELECT setval(pg_get_serial_sequence('public.showtimes', 'showtime_id'),
              COALESCE(MAX(showtime_id), 1), MAX(showtime_id) IS NOT NULL)
FROM public.showtimes;

SELECT setval(pg_get_serial_sequence('public.customers', 'customer_id'),
              COALESCE(MAX(customer_id), 1), MAX(customer_id) IS NOT NULL)
FROM public.customers;

SELECT setval(pg_get_serial_sequence('public.bookings', 'booking_id'),
              COALESCE(MAX(booking_id), 1), MAX(booking_id) IS NOT NULL)
FROM public.bookings;

SELECT setval(pg_get_serial_sequence('public.booking_seats', 'booking_seat_id'),
              COALESCE(MAX(booking_seat_id), 1), MAX(booking_seat_id) IS NOT NULL)
FROM public.booking_seats;