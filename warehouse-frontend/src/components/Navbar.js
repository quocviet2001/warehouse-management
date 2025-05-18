import { useNavigate } from 'react-router-dom';

function Navbar() {
  const navigate = useNavigate();
  const role = localStorage.getItem('role');

  const handleLogout = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
    navigate('/login');
  };

  return (
    <nav className="bg-blue-500 p-4">
      <div className="max-w-7xl mx-auto flex justify-between items-center">
        <div className="text-white text-xl font-bold">
          <img src="/logoapp.png" alt="Logo" className="w-12 h-12 mr-1 inline-block align-middle" />
          Warehouse Management
        </div>
        <div className="space-x-4">
          <a href="/" className="text-white hover:text-gray-200">
            Dashboard
          </a>
          <a href="/products" className="text-white hover:text-gray-200">
            Products
          </a>
          {role === 'ADMIN' && (
            <a href="/categories" className="text-white hover:text-gray-200">
              Categories
            </a>
          )}
          <a href="/stock-in" className="text-white hover:text-gray-200">
            Stock In
          </a>
          <a href="/stock-out" className="text-white hover:text-gray-200">
            Stock Out
          </a>
          <a href="/reports" className="text-white hover:text-gray-200">
            Reports
          </a>
          {role === 'ADMIN' && (
            <a href="/users" className="text-white hover:text-gray-200">
              Users
            </a>
          )}
          <button
            onClick={handleLogout}
            className="text-white hover:text-gray-200"
          >
            Logout
          </button>
        </div>
      </div>
    </nav>
  );
}

export default Navbar;