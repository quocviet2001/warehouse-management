function ProductList({ products, onDelete, onEdit }) {
  return (
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
            <th className="px-4 py-2 border">Actions</th>
          </tr>
        </thead>
        <tbody>
          {products.map((product) => (
            <tr key={product.id}>
              <td className="px-4 py-2 border">{product.name}</td>
              <td className="px-4 py-2 border">{product.sku}</td>
              <td className="px-4 py-2 border">{product.unit}</td>
              <td className="px-4 py-2 border">{product.quantity}</td>
              <td className="px-4 py-2 border">{product.price}</td>
              <td className="px-4 py-2 border">{product.categoryName}</td>
              <td className="px-4 py-2 border">
                {onEdit && (
                  <button
                    onClick={() => onEdit(product)}
                    className="text-blue-500 hover:text-blue-700 mr-2"
                  >
                    Edit
                  </button>
                )}
                {onDelete && (
                  <button
                    onClick={() => onDelete(product.id)}
                    className="text-red-500 hover:text-red-700"
                  >
                    Delete
                  </button>
                )}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default ProductList;
