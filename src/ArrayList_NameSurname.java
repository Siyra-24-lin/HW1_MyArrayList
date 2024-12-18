import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

/**
 * ArrayList_NameSurname - это реализация динамического массива, имплементирующая интерфейс IntensiveList.
 * Предоставляет методы для управления размером и элементами списка.
 *
 * @param <E> тип элементов в этом списке
 */
public class ArrayList_NameSurname<E> implements IntensiveList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] elements;
    private int size = 0;

    /**
     * Создает пустой список с начальной емкостью десять.
     */
    public ArrayList_NameSurname() {
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Создает пустой список с указанной начальной емкостью.
     *
     * @param initialCapacity начальная емкость списка
     * @throws IllegalArgumentException если начальная емкость отрицательная
     */
    public ArrayList_NameSurname(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elements = new Object[initialCapacity];
    }

    /**
     * Создает список, содержащий элементы указанной коллекции, в порядке их возврата итератором коллекции.
     *
     * @param collection коллекция, элементы которой будут помещены в этот список
     * @throws NullPointerException если указанная коллекция null
     */
    public ArrayList_NameSurname(Collection<? extends E> collection) {
        elements = collection.toArray();
        size = elements.length;
        if (elements.getClass() != Object[].class) {
            elements = Arrays.copyOf(elements, size, Object[].class);
        }
    }

    /**
     * Возвращает количество элементов в списке.
     *
     * @return количество элементов в списке
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Добавляет указанный элемент в конец списка.
     *
     * @param element элемент, который нужно добавить в список
     */
    @Override
    public void add(E element) {
        ensureCapacity(size + 1);
        elements[size++] = element;
    }

    /**
     * Вставляет указанный элемент в указанную позицию в списке.
     *
     * @param index индекс, по которому должен быть вставлен указанный элемент
     * @param element элемент, который нужно вставить
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона (index < 0 || index > size)
     */
    @Override
    public void add(int index, E element) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity(size + 1);
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = element;
        size++;
    }

    /**
     * Возвращает элемент, находящийся в указанной позиции в списке.
     *
     * @param index индекс элемента, который нужно вернуть
     * @return элемент, находящийся в указанной позиции в списке
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона (index < 0 || index >= size)
     */
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return (E) elements[index];
    }

    /**
     * Заменяет элемент в указанной позиции в списке на указанный элемент.
     *
     * @param index индекс элемента, который нужно заменить
     * @param element элемент, который будет сохранен в указанной позиции
     * @return элемент, ранее находившийся в указанной позиции
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона (index < 0 || index >= size)
     */
    @Override
    public E set(int index, E element) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elements[index];
        elements[index] = element;
        return oldValue;
    }

    /**
     * Удаляет элемент в указанной позиции в списке.
     *
     * @param index индекс элемента, который нужно удалить
     * @return элемент, который был удален из списка
     * @throws IndexOutOfBoundsException если индекс находится вне диапазона (index < 0 || index >= size)
     */
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        E oldValue = (E) elements[index];
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(elements, index + 1, elements, index, numMoved);
        }
        elements[--size] = null; // clear to let GC do its work
        return oldValue;
    }

    /**
     * Удаляет все элементы из списка.
     */
    @Override
    public void clear() {
        Arrays.fill(elements, 0, size, null);
        size = 0;
        elements = new Object[DEFAULT_CAPACITY];
    }

    /**
     * Сортирует список в соответствии с порядком, заданным указанным компаратором,
     * используя алгоритм быстрой сортировки.
     *
     * @param comparator компаратор, определяющий порядок списка
     */
    @Override
    public void quickSort(Comparator<E> comparator) {
        quickSort(elements, 0, size - 1, comparator);
    }

    /**
     * Вспомогательный метод для выполнения быстрой сортировки на подмассиве.
     *
     * @param array массив, который нужно отсортировать
     * @param low начальный индекс подмассива
     * @param high конечный индекс подмассива
     * @param comparator компаратор, определяющий порядок списка
     */
    private void quickSort(Object[] array, int low, int high, Comparator<E> comparator) {
        if (low < high) {
            int pi = partition(array, low, high, comparator);
            quickSort(array, low, pi - 1, comparator);
            quickSort(array, pi + 1, high, comparator);
        }
    }

    /**
     * Разделяет подмассив на две части и возвращает индекс опорного элемента.
     *
     * @param array массив, который нужно разделить
     * @param low начальный индекс подмассива
     * @param high конечный индекс подмассива
     * @param comparator компаратор, определяющий порядок списка
     * @return индекс опорного элемента
     */
    private int partition(Object[] array, int low, int high, Comparator<E> comparator) {
        E pivot = (E) array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (comparator.compare((E) array[j], pivot) <= 0) {
                i++;
                swap(array, i, j);
            }
        }
        swap(array, i + 1, high);
        return i + 1;
    }

    /**
     * Меняет местами два элемента в массиве.
     *
     * @param array массив, в котором нужно поменять элементы
     * @param i индекс первого элемента
     * @param j индекс второго элемента
     */
    private void swap(Object[] array, int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    /**
     * Проверяет, отсортирован ли список в порядке возрастания.
     *
     * @return true, если список отсортирован, false в противном случае
     */
    @Override
    public boolean isSorted() {
        for (int i = 1; i < size; i++) {
            if (((Comparable<E>) elements[i - 1]).compareTo((E) elements[i]) > 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Обрезает список до указанного размера.
     *
     * @param newSize новый размер списка
     * @throws IllegalArgumentException если указанный размер отрицательный или больше текущего размера
     */
    @Override
    public void split(int newSize) {
        if (newSize < 0 || newSize > size) {
            throw new IllegalArgumentException("Invalid size: " + newSize);
        }
        size = newSize;
        elements = Arrays.copyOf(elements, newSize);
    }

    /**
     * Обеспечивает, чтобы список имел достаточную емкость для хранения указанного количества элементов.
     * Если текущая емкость недостаточна, увеличивает емкость в 1.5 раза.
     *
     * @param minCapacity желаемая минимальная емкость
     */
    private void ensureCapacity(int minCapacity) {
        if (minCapacity > elements.length) {
            int newCapacity = elements.length + (elements.length >> 1) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Возвращает строковое представление списка.
     * Строковое представление состоит из списка элементов, заключенных в квадратные скобки ("[]").
     * Элементы разделены запятыми и пробелами (", ").
     * Пример: [element1, element2, element3, ...]
     *
     * @return строковое представление этого списка
     */
    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(elements[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}