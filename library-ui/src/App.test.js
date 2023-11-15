import { render, screen } from '@testing-library/react';
import App from './App';

describe('App', () => {
  it('renders the header component', () => {
    render(<App />);
    const headerElement = screen.getByTestId('header');
    expect(headerElement).toBeInTheDocument();
  });

  it('renders the footer component', () => {
    render(<App />);
    const footerElement = screen.getByTestId('footer');
    expect(footerElement).toBeInTheDocument();
  });

  it('renders the landing component by default', () => {
    render(<App />);
    const landingElement = screen.getByTestId('landing');
    expect(landingElement).toBeInTheDocument();
  });

  it('renders the list book component when the path is /book/list', () => {
    window.history.pushState({}, 'List Book', '/book/list');
    render(<App />);
    const listBookElement = screen.getByTestId('list-book');
    expect(listBookElement).toBeInTheDocument();
  });

  it('renders the add book component when the path is /book/add', () => {
    window.history.pushState({}, 'Add Book', '/book/add');
    render(<App />);
    const addBookElement = screen.getByTestId('add-book');
    expect(addBookElement).toBeInTheDocument();
  });

  it('renders the delete book component when the path is /book/delete/:id', () => {
    window.history.pushState({}, 'Delete Book', '/book/delete/1');
    render(<App />);
    const deleteBookElement = screen.getByTestId('delete-book');
    expect(deleteBookElement).toBeInTheDocument();
  });

  it('renders the borrow book component when the path is /book/borrow/:id', () => {
    window.history.pushState({}, 'Borrow Book', '/book/borrow/1');
    render(<App />);
    const borrowBookElement = screen.getByTestId('borrow-book');
    expect(borrowBookElement).toBeInTheDocument();
  });
});


