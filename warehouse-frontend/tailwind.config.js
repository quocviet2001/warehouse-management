/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}", // Quét tất cả file JS/JSX trong src để áp dụng Tailwind
  ],
  theme: {
    extend: {}, // Có thể mở rộng theme nếu cần
  },
  plugins: [], // Có thể thêm plugin nếu cần
}