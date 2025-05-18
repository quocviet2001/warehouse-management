import { useState, useEffect } from "react";
import ProductList from "../components/ProductList";
import ProductForm from "../components/ProductForm";
import {
  getProducts,
  createProduct,
  updateProduct,
  deleteProduct,
  getCategories,
  getStockByCategory,
} from "../services/api";

function ProductPage() {
  const [products, setProducts] = useState([]);
  const [search, setSearch] = useState("");
  const [categoryId, setCategoryId] = useState('');
  const [categories, setCategories] = useState([]);
  const [editProduct, setEditProduct] = useState(null);
  const role = localStorage.getItem("role");

  useEffect(() => {
    fetchCategories();
    fetchProducts();
  }, [search, categoryId]);

  const fetchCategories = async () => {
    try {
      const data = await getCategories();
      setCategories(data);
    } catch (err) {
      console.error('Failed to fetch categories:', err);
    }
  };

  const fetchProducts = async () => {
    try {
      if (categoryId) {
        const data = await getStockByCategory(categoryId);
        setProducts(data);
      } else {
        const data = await getProducts(search);
        setProducts(data);
      }
    } catch (err) {
      console.error('Failed to fetch products:', err);
    }
  };

  const handleCreate = async (product) => {
    try {
      await createProduct(product);
      fetchProducts();
    } catch (err) {
      console.error("Failed to create product:", err);
    }
  };

  const handleUpdate = async (product) => {
    try {
      await updateProduct(product.id, product);
      setEditProduct(null);
      fetchProducts();
    } catch (err) {
      console.error("Failed to update product:", err);
    }
  };

  const handleDelete = async (id) => {
    try {
      await deleteProduct(id);
      fetchProducts();
    } catch (err) {
      console.error("Failed to delete product:", err);
    }
  };

  const handleEdit = (product) => {
    setEditProduct(product);
  };

  return (
    <div className="max-w-7xl mx-auto p-6">
      <h1 className="text-3xl font-bold mb-6">Products</h1>
      <div className="mb-6 flex space-x-4">
        <input
          type="text"
          placeholder="Search by name or SKU"
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="w-full p-2 border rounded"
        />
        <select
          value={categoryId}
          onChange={(e) => setCategoryId(e.target.value)}
          className="p-2 border rounded"
        >
          <option value="">All Categories</option>
          {categories.map((category) => (
            <option key={category.id} value={category.id}>
              {category.name}
            </option>
          ))}
        </select>
      </div>
      {role === 'ADMIN' && (
        <ProductForm
          onSubmit={editProduct ? handleUpdate : handleCreate}
          initialData={editProduct}
        />
      )}
      <ProductList
        products={products}
        onDelete={role === 'ADMIN' ? handleDelete : null}
        onEdit={role === 'ADMIN' ? handleEdit : null}
      />
    </div>
  );
}

export default ProductPage;
