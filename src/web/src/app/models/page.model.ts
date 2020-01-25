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
  public content: T[];
  public pageable: IPageable;
  public totalPages: number;
  public totalElements: number;
  public last: boolean;
  public number: number;
  public sort: ISort;
  public size: number;
  public first: boolean;
  public numberOfElements: number;
  public empty: boolean;

  constructor();
  constructor(content: T[], pageable: IPageable, totalPages: number, totalElements: number, last: boolean, number: number,
    sort: ISort, size: number, first: boolean, numberOfElements: number, empty: boolean);
  constructor(content?: T[], pageable?: IPageable, totalPages?: number, totalElements?: number, last?: boolean, number?: number,
    sort?: ISort, size?: number, first?: boolean, numberOfElements?: number, empty?: boolean) {
      this.content = content || [];
      this.pageable = pageable || null;
      this.totalPages = totalPages || 0;
      this.totalElements = totalElements || 0;
      this.last = last || false;
      this.number = number || 0;
      this.sort = sort || null;
      this.size = size || 0;
      this.first = first || false;
      this.numberOfElements = numberOfElements || 0;
      this.empty = empty || true;
    }
}
