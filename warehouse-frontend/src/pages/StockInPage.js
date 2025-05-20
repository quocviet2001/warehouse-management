import { useState, useEffect } from 'react';
import StockInForm from '../components/StockInForm';
import { createStockIn, getStockIns } from '../services/api';

function StockInPage() {
  const [stockIns, setStockIns] = useState([]);

  useEffect(() => {
    fetchStockIns();
  }, []);

  const fetchStockIns = async () => {
    try {
      const data = await getStockIns();
      setStockIns(data);
    } catch (err) {
      console.error('Failed to fetch stock ins:', err);
    }
  };

  const handleSubmit = async (stockIn) => {
    try {
      await createStockIn(stockIn);
      fetchStockIns();
    } catch (err) {
      console.error('Failed to create stock in:', err);
    }
  };

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">Stock In</h1>
      <StockInForm onSubmit={handleSubmit} />
      <div className="mt-6">
        <h2 className="text-2xl font-bold mb-4">Stock In History</h2>
        <div className="overflow-x-auto">
          <table className="min-w-full bg-white border">
            <thead>
              <tr>
                <th className="px-4 py-2 border">SKU</th>
                <th className="px-4 py-2 border">Product Name</th>
                <th className="px-4 py-2 border">Quantity</th>
                <th className="px-4 py-2 border">Date</th>
                <th className="px-4 py-2 border">Reason</th>
                <th className="px-4 py-2 border">Note</th>
              </tr>
            </thead>
            <tbody>
              {stockIns.map((stockIn) => (
                <tr key={stockIn.id}>
                  <td className="px-4 py-2 border">{stockIn.productSku}</td>
                  <td className="px-4 py-2 border">{stockIn.productName}</td>
                  <td className="px-4 py-2 border">{stockIn.quantity}</td>
                  <td className="px-4 py-2 border">{stockIn.date}</td>
                  <td className="px-4 py-2 border">{stockIn.reason}</td>
                  <td className="px-4 py-2 border">{stockIn.note}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
    </div>
  );
}

export default StockInPage;