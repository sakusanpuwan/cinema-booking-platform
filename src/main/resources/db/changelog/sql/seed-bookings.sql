INSERT INTO bookings
(booking_id, customer_id, showtime_id, status, total_amount, expires_at)
VALUES
    (1,  1,  1,  'CONFIRMED', '25.00', NULL),
    (2,  2,  1,  'CONFIRMED', '25.00', NULL),
    (3,  3,  2,  'CONFIRMED', '37.50', NULL),
    (4,  4,  3,  'CONFIRMED', '15.00', NULL),
    (5,  5, 4,  'CANCELLED', '30.00', NULL),
    (6,  6, 5,  'CONFIRMED', '42.00', NULL),
    (7,  7, 6,  'CONFIRMED', '27.50', NULL),
    (8,  8, 7,  'PENDING',   '20.00', '2026-09-22 21:00:00+01:00'),
    (9,  9, 8, 'CONFIRMED', '35.00', NULL),
    (10, 10, 9, 'CONFIRMED', '50.00', NULL),
    (11, 1, 10, 'CONFIRMED', '22.50', NULL),
    (12, 2, 11, 'CANCELLED', '30.00', NULL),
    (13, 3, 12, 'CONFIRMED', '40.00', NULL),
    (14, 4, 13, 'CONFIRMED', '17.50', NULL),
    (15, 5, 14, 'CONFIRMED', '32.00', NULL);