import { Injectable } from '@angular/core';
import { NotificationType } from 'src/app/enum/notification-type.enum';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  
  constructor(private notifier:NotificationService) { }

  public notify( type: NotificationType, message: string ) {
		this.notifier.notify( type, message );
	}
}
