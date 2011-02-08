#ifndef __X10_LANG_GLOBALCELL_H
#define __X10_LANG_GLOBALCELL_H

#include <x10rt.h>


#define X10_LANG_OBJECT_H_NODEPS
#include <x10/lang/Object.h>
#undef X10_LANG_OBJECT_H_NODEPS
#define X10_LANG_GLOBALREF_STRUCT_H_NODEPS
#include <x10/lang/GlobalRef.struct_h>
#undef X10_LANG_GLOBALREF_STRUCT_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
template<class FMGL(T)> class Cell;
} } 
namespace x10 { namespace lang { 
class String;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
class VoidFun_0_0;
} } 
namespace x10 { namespace lang { 

template<class FMGL(T)> class GlobalCell;
template <> class GlobalCell<void>;
template<class FMGL(T)> class GlobalCell : public x10::lang::Object   {
    public:
    RTT_H_DECLS_CLASS
    
    void _instance_init();
    
    x10::lang::GlobalRef<x10aux::ref<x10::lang::Cell<FMGL(T)> > > FMGL(root);
    
    void _constructor(FMGL(T) v);
    
    static x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > _make(FMGL(T) v);
    
    virtual x10aux::ref<x10::lang::String> toString();
    virtual FMGL(T) __apply();
    virtual void __apply(FMGL(T) x);
    virtual void __set(FMGL(T) x);
    virtual FMGL(T) set(FMGL(T) x);
    virtual x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > x10__lang__GlobalCell____x10__lang__GlobalCell__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class GlobalCell<void> : public x10::lang::Object {
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    template<class FMGL(T)> static x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >
      make(
      FMGL(T) x);
    
    template<class FMGL(T)> static FMGL(T) __implicit_convert(x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > x);
    
    
};

} } 
#endif // X10_LANG_GLOBALCELL_H

namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalCell;
} } 

#ifndef X10_LANG_GLOBALCELL_H_NODEPS
#define X10_LANG_GLOBALCELL_H_NODEPS
#include <x10/lang/Object.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/Cell.h>
#include <x10/lang/String.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/VoidFun_0_0.h>
#ifndef X10_LANG_GLOBALCELL__CLOSURE__0_CLOSURE
#define X10_LANG_GLOBALCELL__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_lang_GlobalCell__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_lang_GlobalCell__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    FMGL(T) __apply() {
        
        //#line 35 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
        return x10aux::nullCheck((saved_this->FMGL(root))->__apply())->FMGL(value);
        
    }
    
    // captured environment
    x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > saved_this;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_GlobalCell__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_lang_GlobalCell__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_GlobalCell__closure__0<FMGL(T) > >(storage));
        x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > >();
        x10aux::ref<x10_lang_GlobalCell__closure__0<FMGL(T) > > this_ = new (storage) x10_lang_GlobalCell__closure__0<FMGL(T) >(that_saved_this);
        return this_;
    }
    
    x10_lang_GlobalCell__closure__0(x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > saved_this) : saved_this(saved_this) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10:35";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<FMGL(T)>::template itable <x10_lang_GlobalCell__closure__0<FMGL(T) > >x10_lang_GlobalCell__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_GlobalCell__closure__0<FMGL(T) >::__apply, &x10_lang_GlobalCell__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_GlobalCell__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<FMGL(T)> >, &x10_lang_GlobalCell__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_GlobalCell__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_GlobalCell__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_GLOBALCELL__CLOSURE__0_CLOSURE
#ifndef X10_LANG_GLOBALCELL__CLOSURE__1_CLOSURE
#define X10_LANG_GLOBALCELL__CLOSURE__1_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_GlobalCell__closure__1 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_GlobalCell__closure__1<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": polyglot.ast.Eval_c
        x10aux::nullCheck((saved_this->FMGL(root))->__apply())->FMGL(value) =
          x;
    }
    
    // captured environment
    x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > saved_this;
    FMGL(T) x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_GlobalCell__closure__1<FMGL(T) >* storage = x10aux::alloc<x10_lang_GlobalCell__closure__1<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_GlobalCell__closure__1<FMGL(T) > >(storage));
        x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > >();
        FMGL(T) that_x = buf.read<FMGL(T)>();
        x10aux::ref<x10_lang_GlobalCell__closure__1<FMGL(T) > > this_ = new (storage) x10_lang_GlobalCell__closure__1<FMGL(T) >(that_saved_this, that_x);
        return this_;
    }
    
    x10_lang_GlobalCell__closure__1(x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > saved_this, FMGL(T) x) : saved_this(saved_this), x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10:43";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_GlobalCell__closure__1<FMGL(T) > >x10_lang_GlobalCell__closure__1<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_GlobalCell__closure__1<FMGL(T) >::__apply, &x10_lang_GlobalCell__closure__1<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_GlobalCell__closure__1<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_GlobalCell__closure__1<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_GlobalCell__closure__1<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_GlobalCell__closure__1<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_GLOBALCELL__CLOSURE__1_CLOSURE
#ifndef X10_LANG_GLOBALCELL__CLOSURE__2_CLOSURE
#define X10_LANG_GLOBALCELL__CLOSURE__2_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/VoidFun_0_0.h>
template<class FMGL(T)> class x10_lang_GlobalCell__closure__2 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::VoidFun_0_0::template itable <x10_lang_GlobalCell__closure__2<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    void __apply() {
        
        //#line 55 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": polyglot.ast.Eval_c
        x10aux::nullCheck((saved_this->FMGL(root))->__apply())->FMGL(value) =
          x;
    }
    
    // captured environment
    x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > saved_this;
    FMGL(T) x;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->saved_this);
        buf.write(this->x);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_GlobalCell__closure__2<FMGL(T) >* storage = x10aux::alloc<x10_lang_GlobalCell__closure__2<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_GlobalCell__closure__2<FMGL(T) > >(storage));
        x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > that_saved_this = buf.read<x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > >();
        FMGL(T) that_x = buf.read<FMGL(T)>();
        x10aux::ref<x10_lang_GlobalCell__closure__2<FMGL(T) > > this_ = new (storage) x10_lang_GlobalCell__closure__2<FMGL(T) >(that_saved_this, that_x);
        return this_;
    }
    
    x10_lang_GlobalCell__closure__2(x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > saved_this, FMGL(T) x) : saved_this(saved_this), x(x) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::VoidFun_0_0>(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10:54-56";
    }

};

template<class FMGL(T)> typename x10::lang::VoidFun_0_0::template itable <x10_lang_GlobalCell__closure__2<FMGL(T) > >x10_lang_GlobalCell__closure__2<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_GlobalCell__closure__2<FMGL(T) >::__apply, &x10_lang_GlobalCell__closure__2<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_GlobalCell__closure__2<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::VoidFun_0_0>, &x10_lang_GlobalCell__closure__2<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_GlobalCell__closure__2<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_GlobalCell__closure__2<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_GLOBALCELL__CLOSURE__2_CLOSURE
#ifndef X10_LANG_GLOBALCELL_H_GENERICS
#define X10_LANG_GLOBALCELL_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::lang::GlobalCell<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::GlobalCell<FMGL(T)> >(), 0, sizeof(x10::lang::GlobalCell<FMGL(T)>))) x10::lang::GlobalCell<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_GLOBALCELL_H_GENERICS
#ifndef X10_LANG_GLOBALCELL_H_IMPLEMENTATION
#define X10_LANG_GLOBALCELL_H_IMPLEMENTATION
#include <x10/lang/GlobalCell.h>


template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::GlobalCell<FMGL(T)>");
    
}


//#line 16 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10FieldDecl_c

//#line 17 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::_constructor(
                          FMGL(T) v) {
    this->::x10::lang::Object::_constructor();
    {
     
    }
    
    //#line 18 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this)->FMGL(root) = x10::lang::GlobalRef_methods<x10aux::ref<x10::lang::Cell<FMGL(T)> > >::_make(x10::lang::Cell<FMGL(T)>::_make(v));
    
}
template<class FMGL(T)> x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > x10::lang::GlobalCell<FMGL(T)>::_make(
                          FMGL(T) v) {
    x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::GlobalCell<FMGL(T)> >(), 0, sizeof(x10::lang::GlobalCell<FMGL(T)>))) x10::lang::GlobalCell<FMGL(T)>();
    this_->_constructor(v);
    return this_;
}



//#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::String> x10::lang::GlobalCell<FMGL(T)>::toString(
  ) {
    
    //#line 26 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
    return (((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this)->
              FMGL(root))->toString();
    
}

//#line 35 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::GlobalCell<FMGL(T)>::__apply(
  ) {
    
    //#line 35 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
    return x10::lang::Runtime::template evalAt<FMGL(T) >(
             x10::lang::Place_methods::place((((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this)->
                                                FMGL(root))->location),
             x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> > >(x10aux::ref<x10::lang::Fun_0_0<FMGL(T)> >(x10aux::ref<x10_lang_GlobalCell__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<FMGL(T)> >(sizeof(x10_lang_GlobalCell__closure__0<FMGL(T)>)))x10_lang_GlobalCell__closure__0<FMGL(T)>(this)))));
    
}

//#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::__apply(
  FMGL(T) x) {
    
    //#line 43 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::runAt(x10::lang::Place_methods::place((((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this)->
                                                                 FMGL(root))->location),
                              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_GlobalCell__closure__1<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_GlobalCell__closure__1<FMGL(T)>)))x10_lang_GlobalCell__closure__1<FMGL(T)>(this, x)))));
}

//#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::__set(
  FMGL(T) x) {
    
    //#line 52 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this)->set(
      x);
}

//#line 53 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::GlobalCell<FMGL(T)>::set(
  FMGL(T) x) {
    
    //#line 54 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": polyglot.ast.Eval_c
    x10::lang::Runtime::runAt(x10::lang::Place_methods::place((((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this)->
                                                                 FMGL(root))->location),
                              x10aux::class_cast_unchecked<x10aux::ref<x10::lang::VoidFun_0_0> >(x10aux::ref<x10::lang::VoidFun_0_0>(x10aux::ref<x10_lang_GlobalCell__closure__2<FMGL(T) > >(new (x10aux::alloc<x10::lang::VoidFun_0_0>(sizeof(x10_lang_GlobalCell__closure__2<FMGL(T)>)))x10_lang_GlobalCell__closure__2<FMGL(T)>(this, x)))));
    
    //#line 57 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
    return x;
    
}

//#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c

//#line 78 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c

//#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >
  x10::lang::GlobalCell<FMGL(T)>::x10__lang__GlobalCell____x10__lang__GlobalCell__this(
  ) {
    
    //#line 14 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::lang::GlobalCell<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::lang::GlobalCell<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    x10::lang::Object::_serialize_body(buf);
    buf.write(this->FMGL(root));
    
}

template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    x10::lang::Object::_deserialize_body(buf);
    FMGL(root) = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::Cell<FMGL(T)> > > >();
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::GlobalCell<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::GlobalCell<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::GlobalCell<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[1] = { x10aux::getRTT<x10::lang::Object>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.GlobalCell";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 1, parents, 1, params, variances);
}
template<class FMGL(T)> x10aux::ref<x10::lang::GlobalCell<FMGL(T)> >
  x10::lang::GlobalCell<void>::make(FMGL(T) x)
{
    
    //#line 67 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
    return x10::lang::GlobalCell<FMGL(T)>::_make(x);
    
}
template<class FMGL(T)>
FMGL(T)
  x10::lang::GlobalCell<void>::__implicit_convert(x10aux::ref<x10::lang::GlobalCell<FMGL(T)> > x)
{
    
    //#line 78 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/GlobalCell.x10": x10.ast.X10Return_c
    return x10aux::nullCheck(x)->__apply();
    
}
#endif // X10_LANG_GLOBALCELL_H_IMPLEMENTATION
#endif // __X10_LANG_GLOBALCELL_H_NODEPS
