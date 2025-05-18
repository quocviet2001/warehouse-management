import { useEffect, useState } from 'react';
import { getProducts, getStockIns, getStockOuts } from '../services/api';

function Dashboard() {
  const [stats, setStats] = useState({
    totalProducts: 0,
    totalStockIn: 0,
    totalStockOut: 0,
  });

  useEffect(() => {
    const fetchStats = async () => {
      try {
        const products = await getProducts();
        const stockIns = await getStockIns();
        const stockOuts = await getStockOuts();
        setStats({
          totalProducts: products.length,
          totalStockIn: stockIns.length,
          totalStockOut: stockOuts.length,
        });
      } catch (err) {
        console.error('Failed to fetch stats:', err);
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
    </div>
  );
}

export default Dashboard;