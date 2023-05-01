import { Component, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ChatService } from 'src/app/services/chat.service';
import { StorageService } from 'src/app/services/storage.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { Chat } from 'src/app/models/chat.model';

@Component({
  selector: 'app-user-chat',
  templateUrl: './user-chat.component.html',
  styleUrls: ['./user-chat.component.css']
})
export class UserChatComponent implements OnInit {


  userList: User[] = [];

  constructor(private chatService: ChatService,
    private fb: FormBuilder, private storageService: StorageService, private router: Router, private userService: UserService) {

  }
  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers() {

    this.userService.getAllUser().subscribe({
      next: (res) => {
        this.userList = res;
      }
    })
  }
}
