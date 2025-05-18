import axios from 'axios';

// Tạo instance axios với cấu hình mặc định
const api = axios.create({
  baseURL: 'http://localhost:8080/api', // URL backend
  headers: {
    'Content-Type': 'application/json',
  },
});

// Thêm interceptor để gắn token vào header
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// API đăng nhập
export const login = async (username, password) => {
  const response = await api.post('/auth/login', { username, password });
  return response.data;
};

// API quản lý sản phẩm
export const getProducts = async (name = '', sku = '') => {
  const response = await api.get('/products', { params: { name, sku } });
  return response.data;
};

export const createProduct = async (product) => {
  const response = await api.post('/products', product);
  return response.data;
};

export const updateProduct = async (id, product) => {
  const response = await api.put(`/products/${id}`, product);
  return response.data;
};

export const deleteProduct = async (id) => {
  await api.delete(`/products/${id}`);
};

// API quản lý danh mục
export const getCategories = async () => {
  const response = await api.get('/categories');
  return response.data;
};

export const createCategory = async (category) => {
  const response = await api.post('/categories', category);
  return response.data;
};

export const updateCategory = async (id, category) => {
  const response = await api.put(`/categories/${id}`, category);
  return response.data;
};

export const deleteCategory = async (id) => {
  await api.delete(`/categories/${id}`);
};

export const getStockByCategory = async (id) => {
  const response = await api.get(`/categories/${id}/stock`);
  return response.data;
};

// API nhập kho
export const createStockIn = async (stockIn) => {
  const response = await api.post('/stock-in', stockIn);
  return response.data;
};

export const getStockIns = async () => {
  const response = await api.get('/stock-in');
  return response.data;
};

// API xuất kho
export const createStockOut = async (stockOut) => {
  const response = await api.post('/stock-out', stockOut);
  return response.data;
};

export const getStockOuts = async () => {
  const response = await api.get('/stock-out');
  return response.data;
};

// API báo cáo
export const getInOutReport = async (startDate, endDate) => {
  const response = await api.get('/reports/in-out', {
    params: { startDate, endDate },
  });
  return response.data;
};

export const getStockStatusReport = async () => {
  const response = await api.get('/reports/stock-status');
  return response.data;
};

// API quản lý người dùng
export const createUser = async (user) => {
  const response = await api.post('/users', user);
  return response.data;
};

export const updateUser = async (id, user) => {
  const response = await api.put(`/users/${id}`, user);
  return response.data;
};

export const deleteUser = async (id) => {
  await api.delete(`/users/${id}`);
};

export const getUsers = async () => {
  const response = await api.get('/users');
  return response.data;
};

export default api;