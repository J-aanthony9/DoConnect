import { Component, OnInit } from '@angular/core';
import { FormGroup, ReactiveFormsModule, FormControl, FormBuilder, Validators, AbstractControl } from '@angular/forms';
import { ChatService } from 'src/app/services/chat.service';
import { StorageService } from 'src/app/services/storage.service';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { User } from 'src/app/models/user.model';
import { Chat } from 'src/app/models/chat.model';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  messageForm: FormGroup = new FormGroup({
    message: new FormControl('')

  });

  submitted = false;


  chatList: Chat[] = [];
  inboxList: Chat[] = [];


  constructor(private chatService: ChatService,
    private fb: FormBuilder, private storageService: StorageService, private router: Router, private userService: UserService, private route: ActivatedRoute, private datepipe: DatePipe) {

  }

  ngOnInit(): void {
    this.messageForm = this.fb.group({
      message: ['', Validators.required],
    })
    console.log(this.route.snapshot.paramMap.get('username'));



    this.getAllMessagesFromUser();
    this.getAllMessagesToUser();
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
      this.sendMessage();
      this.getAllMessagesFromUser();
      this.getAllMessagesToUser();
    }
  }

  sendMessage() {
    const data = {
      message: this.messageForm.value.message,
      to_user: this.route.snapshot.paramMap.get('username'),
      from_user: this.storageService.getUser().username,
      datetime: this.datepipe.transform((new Date), 'MM/dd/yyyy h:mm:ss')
    }
    // console.log(data);
    this.chatService.createMsg(data).subscribe({
      next: (res) => {
        console.log(res);
        // this.router.navigateByUrl('/chat');
      }
    });
  }

  // getAllUsers() {

  //   this.userService.getAllUser().subscribe({
  //     next: (res) => {
  //       this.userList = res;
  //     }
  //   })
  // }

  // getAllMessages() {
  //   this.chatService.getAllMsg().subscribe({
  //     next: (res) => {
  //       this.chatList = res;
  //     }
  //   })
  // }

  getAllMessagesFromUser() {
    const data = {
      from_user: this.storageService.getUser().username,
      to_user: this.route.snapshot.paramMap.get('username')
    }
    this.chatService.getMessageBetweenUsers(data).subscribe({
      next: (res) => {
        this.chatList = res;
        console.log(res);
        //     }
      }
    })
  }

  getAllMessagesToUser() {
    const data = {
      from_user: this.route.snapshot.paramMap.get('username'),
      to_user: this.storageService.getUser().username,

    }
    this.chatService.getMessageBetweenUsers(data).subscribe({
      next: (res) => {
        this.inboxList = res;
        console.log(res);
        //     }
      }
    })
  }
}
