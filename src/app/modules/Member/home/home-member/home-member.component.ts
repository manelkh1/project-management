import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ActivatedRoute, Router } from '@angular/router';
import { first } from 'rxjs';
import { Manager } from 'src/app/models/manager';
import { User } from 'src/app/models/user';
import { ManagerService } from 'src/app/services/manager.service';
import { ProjectService } from 'src/app/services/project.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home-member',
  templateUrl: './home-member.component.html',
  styleUrls: ['./home-member.component.scss']
})
export class HomeMemberComponent implements OnInit {
  dataSource!: MatTableDataSource<any>;
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  form!: FormGroup;
  id!: number;
  projects: any;
  objectUser: any;
  currentUser: User = new User();
  users: User[] = [];
  managers: Manager = new Manager();
  showAdmin!: boolean;
  loading = false;

  constructor(private tokenStorage: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService,
    private userService: UserService,
    private managerService: ManagerService,
    private _fb: FormBuilder) { }

  ngOnInit(): void {
    this.objectUser = this.tokenStorage.getUser();
    this.id = this.route.snapshot.params['id'];

    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
    });
    
    this.getAllProjectsByMember();
    console.log(this.getAllProjectsByMember());
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

 /*  getAllProjectsByManager() {
    this.projectService.getAllProjectsByManager().subscribe((data) => {
      //MatTableDataSource :  une source de donn??es de table matTableDataSource int??gr??e.
      this.dataSource = new MatTableDataSource(data);
      //apply the pagination to all the dataSource
      this.dataSource.paginator = this.paginator;
    });
  } */

 /*  getAllProjectsByMember() {
    this.projectService.getAllProjectsByMember().subscribe(
      (data: any) => {
        console.log("projects "+ data.data);
        this.projects = data.data;
      },
      (error: any) => {
        console.log(error);
      }
    );
  } */
  getAllProjectsByMember() {
    this.projectService.getAllProjectsByMember().subscribe(
       (data: any) => {
        console.log("projects "+ data.data);
        this.projects = data.data;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

/*   getAllProjectsByBank() {
    this.projectService.getAllProjectsByBank().subscribe((data: unknown[] | undefined) => {
      this.projects = new MatTableDataSource(data);
      this.projects.paginator = this.paginator;
    });
  } */

/*   getProjects() {

  } */

 /*  getAllManagers() {
    this.managerService
      .getAllManagers()
      .pipe(first())
      .subscribe((managers) => {
        this.managers = new Manager();
      });
  }
 */
/*   subStrDescription(description: String) {
    if (description.length > 25) {
      return description.substring(0, 25) + '...';
    } else {
      return description.substring(0, 25);
    }
  } */

  getAllUsers() {
    /*  this.loading = true;
    this.userService
      .getAllUsers()
      .pipe(first())
      .subscribe((users) => {
        this.loading = false;
        this.users = users;
      });
    console.log(this.users); */
  }
  // getUsers(){j
  //   this.userService.getUserById(this.id).subscribe((data) =>{
  //     this.users = data;
  //     console.log(this.users);
  //   });
  // }

  getRoute(route: string, id: number) {
    this.router.navigate([route, id]);
  }

  subStrName(user: User) {
    return user.firstName.substring(0, 1) + user.lastName.substring(0, 1);
  }
  }

