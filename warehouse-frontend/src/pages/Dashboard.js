import { useEffect, useState } from "react";
import {
  getProducts,
  getStockIns,
  getStockOuts,
  getLowStockProducts,
} from "../services/api";

function Dashboard() {
  const [stats, setStats] = useState({
    totalProducts: 0,
    totalStockIn: 0,
    totalStockOut: 0,
  });
  const [lowStockProducts, setLowStockProducts] = useState([]);

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const [products, stockIns, stockOuts, lowStock] = await Promise.all([
          getProducts(),
          getStockIns(),
          getStockOuts(),
          getLowStockProducts(10), 
        ]);

        setStats({
          totalProducts: products.length,
          totalStockIn: stockIns.length,
          totalStockOut: stockOuts.length,
        });

        setLowStockProducts(lowStock);
      } catch (err) {
        console.error("Failed to fetch stats:", err);
      }
    };
    fetchStats();
  }, []);

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">Dashboard</h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-white p-6 rounded-lg shadow">
          <h2 className="text-xl font-semibold mb-2">Total Products</h2>
          <p className="text-2xl">{stats.totalProducts}</p>
        </div>
        <div className="bg-white p-6 rounded-lg shadow">
          <h2 className="text-xl font-semibold mb-2">Stock In</h2>
          <p className="text-2xl">{stats.totalStockIn}</p>
        </div>
        <div className="bg-white p-6 rounded-lg shadow">
          <h2 className="text-xl font-semibold mb-2">Stock Out</h2>
          <p className="text-2xl">{stats.totalStockOut}</p>
        </div>
      </div>
      <div className="mt-10">
        <h2 className="text-2xl font-bold mb-4">Low Stock Alerts</h2>
        {lowStockProducts.length > 0 ? (
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white border">
              <thead>
                <tr>
                  <th className="px-4 py-2 border">Name</th>
                  <th className="px-4 py-2 border">SKU</th>
                  <th className="px-4 py-2 border">Quantity</th>
                  <th className="px-4 py-2 border">Category</th>
                </tr>
              </thead>
              <tbody>
                {lowStockProducts.map((product) => (
                  <tr key={product.id} className={product.quantity === 0 ? 'bg-red-100' : 'bg-yellow-100'}>
                    <td className="px-4 py-2 border">{product.name}</td>
                    <td className="px-4 py-2 border">{product.sku}</td>
                    <td className="px-4 py-2 border">{product.quantity}</td>
                    <td className="px-4 py-2 border">{product.categoryName}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <p className="text-gray-600">No low stock products.</p>
        )}
      </div>
    </div>
  );
}

export default Dashboard;
