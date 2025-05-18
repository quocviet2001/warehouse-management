import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import Dashboard from './pages/Dashboard';
import ProductPage from './pages/ProductPage';
import StockInPage from './pages/StockInPage';
import StockOutPage from './pages/StockOutPage';
import ReportPage from './pages/ReportPage';
import UserManagementPage from './pages/UserManagementPage';
import CategoryPage from './pages/CategoryPage';
import Navbar from './components/Navbar';
import './index.css';

// Component kiểm tra xác thực
const PrivateRoute = ({ children }) => {
  const token = localStorage.getItem('token');
  return token ? children : <Navigate to="/login" />;
};

// Component kiểm tra quyền Admin
const AdminRoute = ({ children }) => {
  const token = localStorage.getItem('token');
  const role = localStorage.getItem('role');
  return token && role === 'ADMIN' ? children : <Navigate to="/login" />;
};

function App() {
  return (
    <Router>
      <div className="min-h-screen bg-gray-100">
        <Navbar />
        <Routes>
          <Route path="/login" element={<LoginPage />} />
          <Route
            path="/"
            element={
              <PrivateRoute>
                <Dashboard />
              </PrivateRoute>
            }
          />
          <Route
            path="/products"
            element={
              <PrivateRoute>
                <ProductPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/categories"
            element={
              <AdminRoute>
                <CategoryPage />
              </AdminRoute>
            }
          />
          <Route
            path="/stock-in"
            element={
              <PrivateRoute>
                <StockInPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/stock-out"
            element={
              <PrivateRoute>
                <StockOutPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/reports"
            element={
              <PrivateRoute>
                <ReportPage />
              </PrivateRoute>
            }
          />
          <Route
            path="/users"
            element={
              <AdminRoute>
                <UserManagementPage />
              </AdminRoute>
            }
          />
        </Routes>
      </div>
    </Router>
  );
}

export default App;