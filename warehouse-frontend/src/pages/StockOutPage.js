import { useState, useEffect } from 'react';
import StockOutForm from '../components/StockOutForm';
import { createStockOut, getStockOuts } from '../services/api';

function StockOutPage() {
  const [stockOuts, setStockOuts] = useState([]);

  useEffect(() => {
    fetchStockOuts();
  }, []);

  const fetchStockOuts = async () => {
    try {
      const data = await getStockOuts();
      setStockOuts(data);
    } catch (err) {
      console.error('Failed to fetch stock outs:', err);
    }
  };

  const handleSubmit = async (stockOut) => {
    try {
      await createStockOut(stockOut);
      fetchStockOuts();
    } catch (err) {
      console.error('Failed to create stock out:', err);
    }
  };

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">Stock Out</h1>
      <StockOutForm onSubmit={handleSubmit} />
      <div className="mt-6">
        <h2 className="text-2xl font-bold mb-4">Stock Out History</h2>
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border">
            <thead>
              <tr>
                <th className="px-4 py-2 border">Product ID</th>
                <th className="px-4 py-2 border">Quantity</th>
                <th className="px-4 py-2 border">Date</th>
                <th className="px-4 py-2 border">Reason</th>
                <th className="px-4 py-2 border">Note</th>
              </tr>
            </thead>
            <tbody>
              {stockOuts.map((stockOut) => (
                <tr key={stockOut.id}>
                  <td className="px-4 py-2 border">{stockOut.productId}</td>
                  <td className="px-4 py-2 border">{stockOut.quantity}</td>
                  <td className="px-4 py-2 border">{stockOut.date}</td>
                  <td className="px-4 py-2 border">{stockOut.reason}</td>
                  <td className="px-4 py-2 border">{stockOut.note}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default StockOutPage;