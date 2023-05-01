import { Component, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ChatService } from 'src/app/services/chat.service';
import { StorageService } from 'src/app/services/storage.service';
import { Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  messageForm: FormGroup = new FormGroup({
    message: new FormControl(''),
    userSelect: new FormControl('')
  });

  submitted = false;
  userList: User[] = [];

  constructor(private chatService: ChatService,
    private fb: FormBuilder, private storageService: StorageService, private router: Router, private userService: UserService) {

  }

  ngOnInit(): void {
    this.messageForm = this.fb.group({
      message: ['', Validators.required],
      userSelect: ['', Validators.required]
    })
    this.getAllUsers();
  }

  get f(): { [key: string]: AbstractControl } {
    return this.messageForm.controls;
  }

  onSubmit(): void {
    this.submitted = true;
    if (this.messageForm.invalid) {
      console.log("invalid");
      return;
    } else {
      this.sendMessage()

    }
  }

  sendMessage() {
    const data = {
      description_question: this.messageForm.value.message,
      to_user: this.messageForm.value.userSelect,
      from_user: this.storageService.getUser().username
    }
    console.log(data);
    this.chatService.createMsg(data).subscribe({
      next: (res) => {
        console.log(res);
        // this.router.navigateByUrl('/chat');
      }
    });
  }

  getAllUsers() {

    this.userService.getAllUser().subscribe({
      next: (res) => {
        this.userList = res;
      }
    })
  }
}
