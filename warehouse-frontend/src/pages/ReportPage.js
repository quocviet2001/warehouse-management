import { useState, useEffect } from 'react';
import { getInOutReport, getStockStatusReport } from '../services/api';

function ReportPage() {
  const [inOutReport, setInOutReport] = useState([]);
  const [stockStatusReport, setStockStatusReport] = useState([]);
  const [startDate, setStartDate] = useState('');
  const [endDate, setEndDate] = useState('');

  useEffect(() => {
    fetchStockStatusReport();
  }, []);

  const fetchInOutReport = async () => {
    try {
      const data = await getInOutReport(startDate, endDate);
      setInOutReport(data);
    } catch (err) {
      console.error('Failed to fetch in-out report:', err);
    }
  };

  const fetchStockStatusReport = async () => {
    try {
      const data = await getStockStatusReport();
      setStockStatusReport(data);
    } catch (err) {
      console.error('Failed to fetch stock status report:', err);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    fetchInOutReport();
  };

  const lowStockProducts = stockStatusReport.filter((product) => product.quantity <= 20);
  const highStockProducts = stockStatusReport.filter((product) => product.quantity >= 200);

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">Reports</h1>
      <div className="mb-6">
        <h2 className="text-2xl font-bold mb-4">In/Out Report</h2>
        <form onSubmit={handleSubmit} className="flex space-x-4">
          <div>
            <label className="block text-gray-700">Start Date</label>
            <input
              type="datetime-local"
              value={startDate}
              onChange={(e) => setStartDate(e.target.value)}
              className="p-2 border rounded"
              required
            />
          </div>
          <div>
            <label className="block text-gray-700">End Date</label>
            <input
              type="datetime-local"
              value={endDate}
              onChange={(e) => setEndDate(e.target.value)}
              className="p-2 border rounded"
              required
            />
          </div>
          <button
            type="submit"
            className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600 mt-6"
          >
            Generate
          </button>
        </form>
        <div className="mt-4 overflow-x-auto">
          <table className="min-w-full bg-white border">
            <thead>
              <tr>
                <th className="px-4 py-2 border">Type</th>
                <th className="px-4 py-2 border">SKU</th>
                <th className="px-4 py-2 border">Product Name</th>
                <th className="px-4 py-2 border">Quantity</th>
                <th className="px-4 py-2 border">Date</th>
                <th className="px-4 py-2 border">Reason</th>
                <th className="px-4 py-2 border">Note</th>
              </tr>
            </thead>
            <tbody>
              {inOutReport.map((item) => (
                <tr key={`${item.type}-${item.id}`}>
                  <td className="px-4 py-2 border">{item.type}</td>
                  <td className="px-4 py-2 border">{item.productSku}</td>
                  <td className="px-4 py-2 border">{item.productName}</td>
                  <td className="px-4 py-2 border">{item.quantity}</td>
                  <td className="px-4 py-2 border">{item.date}</td>
                  <td className="px-4 py-2 border">{item.reason}</td>
                  <td className="px-4 py-2 border">{item.note}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      </div>
      <div className="mb-6">
        <h2 className="text-2xl font-bold mb-4">Low Stock Report</h2>
        {lowStockProducts.length > 0 ? (
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white border">
              <thead>
                <tr>
                  <th className="px-4 py-2 border">Name</th>
                  <th className="px-4 py-2 border">SKU</th>
                  <th className="px-4 py-2 border">Unit</th>
                  <th className="px-4 py-2 border">Quantity</th>
                  <th className="px-4 py-2 border">Price</th>
                  <th className="px-4 py-2 border">Category</th>
                </tr>
              </thead>
              <tbody>
                {lowStockProducts.map((product) => (
                  <tr key={product.id} className={product.quantity === 0 ? 'bg-red-100' : 'bg-yellow-100'}>
                    <td className="px-4 py-2 border">{product.name}</td>
                    <td className="px-4 py-2 border">{product.sku}</td>
                    <td className="px-4 py-2 border">{product.unit}</td>
                    <td className="px-4 py-2 border">{product.quantity}</td>
                    <td className="px-4 py-2 border">{product.price}</td>
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
      <div>
        <h2 className="text-2xl font-bold mb-4">High Stock Report</h2>
        {highStockProducts.length > 0 ? (
          <div className="overflow-x-auto">
            <table className="min-w-full bg-white border">
              <thead>
                <tr>
                  <th className="px-4 py-2 border">Name</th>
                  <th className="px-4 py-2 border">SKU</th>
                  <th className="px-4 py-2 border">Unit</th>
                  <th className="px-4 py-2 border">Quantity</th>
                  <th className="px-4 py-2 border">Price</th>
                  <th className="px-4 py-2 border">Category</th>
                </tr>
              </thead>
              <tbody>
                {highStockProducts.map((product) => (
                  <tr key={product.id}>
                    <td className="px-4 py-2 border">{product.name}</td>
                    <td className="px-4 py-2 border">{product.sku}</td>
                    <td className="px-4 py-2 border">{product.unit}</td>
                    <td className="px-4 py-2 border">{product.quantity}</td>
                    <td className="px-4 py-2 border">{product.price}</td>
                    <td className="px-4 py-2 border">{product.categoryName}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        ) : (
          <p className="text-gray-600">No high stock products.</p>
        )}
      </div>
    </div>
  );
}

export default ReportPage;