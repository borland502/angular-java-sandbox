import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class InventoryService {

    inventorySignal = signal<any | null>(null);

  constructor() { }


}
