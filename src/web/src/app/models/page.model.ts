interface ISort {
  sorted: boolean,
  unsorted: boolean,
  empty: boolean
}

interface IPageable {
  sort: ISort,
  offset: number,
  pageNumber: number,
  pageSize: number,
  paged: boolean,
  unpaged: boolean
}

interface IPage<T> {
  content: T[],
  pageable: IPageable,
  totalPages: number,
  totalElements: number,
  last: boolean,
  number: number,
  sort: ISort,
  size: number,
  first: boolean,
  numberOfElements: number,
  empty: boolean
}

export class Page<T> implements IPage<T> {
  constructor(public content: T[], public pageable: IPageable, public totalPages: number, public totalElements: number,
    public last: boolean, public number: number, public sort: ISort, public size: number, public first: boolean,
    public numberOfElements: number, public empty: boolean) {

      this.content = content;
      this.pageable = pageable;
      this.totalPages = totalPages;
      this.totalElements = totalElements;
      this.last = last;
      this.number = number;
      this.sort = sort;
      this.size = size;
      this.first = first;
      this.numberOfElements = numberOfElements;
      this.empty = empty;
    }
}
