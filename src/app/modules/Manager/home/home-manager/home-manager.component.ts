import { Component, OnInit, ViewChild } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
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
  selector: 'app-home-manager',
  templateUrl: './home-manager.component.html',
  styleUrls: ['./home-manager.component.scss'],
})
export class HomeManagerComponent implements OnInit {
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

  constructor(
    private tokenStorage: TokenStorageService,
    private route: ActivatedRoute,
    private router: Router,
    private projectService: ProjectService,
    private userService: UserService,
    private managerService: ManagerService,
    private _fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.objectUser = this.tokenStorage.getUser();
    ///permet de recuperer les infos de ce route
    this.id = this.route.snapshot.params['id'];

    this.form = this._fb.group({
      title: new FormControl('', [Validators.required]),
    });

    this.getAllProjectsByManager();
    console.log(this.getAllProjectsByManager());
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  getAllProjectsByManager() {
    this.projectService.getAllProjectsByManager().subscribe(
      (data: any) => {
        console.log(data.data);
        this.projects = data.data;
        console.log('*************');
        console.log(this.projects);
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

 /* getAllProjectsByMember() {
    this.projectService.getAllProjectsByMember().subscribe(
      (data: any) => {
        console.log(data.data);
        this.projects = data.data;
      },
      (error: any) => {
        console.log(error);
      }
    );
  }

  getAllProjectsByBank() {
    this.projectService
      .getAllProjectsByBank()
      .subscribe((data: unknown[] | undefined) => {
        this.projects = new MatTableDataSource(data);
        this.projects.paginator = this.paginator;
      });
  }*/

  getProjects() {}

  getAllManagers() {
    this.managerService
      .getAllManagersAndAdmin()
      .pipe(first())
      .subscribe((managers) => {
        this.managers = new Manager();
      });
  }

  subStrDescription(description: String) {
    if (description.length > 25) {
      return description.substring(0, 25) + '...';
    } else {
      return description.substring(0, 25);
    }
  }


  getRoute(route: string, id: number) {
    this.router.navigate([route, id]);
  }

  subStrName(user: User) {
    return user.firstName.substring(0, 1) + user.lastName.substring(0, 1);
  }
}
